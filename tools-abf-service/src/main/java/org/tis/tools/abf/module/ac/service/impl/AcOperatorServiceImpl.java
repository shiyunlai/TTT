package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorStatusRequest;
import org.tis.tools.abf.module.ac.dao.AcOperatorMapper;
import org.tis.tools.abf.module.ac.entity.*;
import org.tis.tools.abf.module.ac.entity.enums.AuthMode;
import org.tis.tools.abf.module.ac.entity.enums.OperatorStatus;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcOperatorManagementException;
import org.tis.tools.abf.module.ac.service.*;
import org.tis.tools.core.exception.i18.ExceptionCodes;

import java.math.BigDecimal;
import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * acOperator的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcOperatorServiceImpl extends ServiceImpl<AcOperatorMapper, AcOperator> implements IAcOperatorService {

    @Autowired
    private IAcOperatorRoleService acOperatorRoleService;

    @Autowired
    private IAcOperatorFuncService acOperatorFuncService;

    @Autowired
    private IAcOperatorConfigService acOperatorConfigService;

    @Autowired
    private IAcOperatorIdentityService acOperatorIdentityService;

    @Autowired
    private IAcOperatorMenuService acOperatorMenuService;

    @Autowired
    private IAcOperatorShortcutService acOperatorShortcutService;


    /**
    * <pre>
     *  新增操作员
    * </pre>
    * @param request
    * @return 新增结果
     * @throws AcOperatorManagementException
    *
    */

    @Override
    public boolean addAcOperator(AcOperatorAddRequest request) throws AcOperatorManagementException {

        boolean isexist = new Boolean(false);

        try {

        //判断user_id是否已经存在,确保user_id的唯一性
        Wrapper<AcOperator> wrapper = new EntityWrapper<AcOperator>();
        List<AcOperator > lists = selectList(wrapper);

        for (AcOperator acOperator:lists) {
            if (request.getUserId().equals(acOperator.getUserId())){
                return isexist;
            }
        }
        //循环结束之后表示没有重复,故设置标志位为true
        isexist = true;


        AcOperator acOperator = new AcOperator();
        //操作员状态默认是停用
        acOperator.setOperatorStatus(OperatorStatus.stop);
        //锁定次数前端不传值默认为5,否则为前段值
            if ("".equals(request.getLockLimit())){
                acOperator.setLockLimit(BigDecimal.valueOf(5));
            }else {
                acOperator.setLockLimit(request.getLockLimit());
            }

            if (null == request.getAuthMode() || AuthMode.password.equals(request.getAuthMode())){
                acOperator.setAuthMode(AuthMode.password);
                acOperator.setPassword("111111");
            }else {
                acOperator.setAuthMode(request.getAuthMode());
                acOperator.setPassword(request.getPassword());
            }
            //设置当前错误登录次数为0
            acOperator.setErrCount(new BigDecimal("0"));

            //收集参数
            acOperator.setUserId(request.getUserId());
            acOperator.setOperatorName(request.getOperatorName());
            acOperator.setInvalDate(request.getInvalDate());
            acOperator.setLockTime(request.getLockTime());
            acOperator.setUnlockTime(request.getUnlockTime());
            acOperator.setLastLogin(request.getLastLogin());
            acOperator.setStartDate(request.getStartDate());
            acOperator.setEndDate(request.getEndDate());
            acOperator.setValidTime(request.getValidTime());
            acOperator.setMacCode(request.getMacCode());
            acOperator.setIpAddress(request.getIpAddress());

        insert(acOperator);

        return isexist;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcOperatorManagementException(ExceptionCodes.FAILURE_WHEN_INSERT,wrap("ACOPERATOR",e.getMessage()));
        }
    }

    /**
     *
     * @param acOperator
     * @return 修改操作员结果
     * @throws AcOperatorManagementException
     */
    @Override
    public boolean updateAcOperatorByCondition(AcOperator acOperator) throws AcOperatorManagementException {

        boolean isexist = new Boolean(false);

        try {

                //判断user_id是否已经存在,确保user_id的唯一性
                Wrapper<AcOperator> wrapper = new EntityWrapper<AcOperator>();
                List<AcOperator > lists = selectList(wrapper);

                for (AcOperator acOperatorNew:lists) {
                    if (acOperator.getUserId().equals(acOperatorNew.getUserId()) && !(acOperator.getGuid().equals(acOperatorNew.getGuid())) ){
                        return isexist;
                    }
                }
                //循环结束之后表示没有重复,故设置标志位为true
                isexist = true;

            updateById(acOperator);
            return isexist;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcOperatorManagementException(ExceptionCodes.FAILURE_WHEN_UPDATE,wrap("ACOPERATOR",e.getMessage()));
        }

    }


    @Override
    public AcOperator changeOperatorStatus(AcOperatorStatusRequest acOperatorStatusRequest) throws AcOperatorManagementException {

        /*判断改变操作员状态时是否合法*/
        //获取当前操作员状态
        AcOperator acOperator = selectById(acOperatorStatusRequest.getGuid());
        OperatorStatus operatorStatusNow = acOperator.getOperatorStatus();
        OperatorStatus operatorStatusChange = acOperatorStatusRequest.getOperatorStatus();

        switch (operatorStatusNow){
            case stop:
                if (!(operatorStatusChange.equals(OperatorStatus.logout))){
                    throw new AcOperatorManagementException(AcExceptionCodes.CURRENT_STATUS_IS_NOT_ALLOWED_CHANGE,
                            wrap("停用状态只能修改为退出状态"));
                }
                break;
            case logout:
                if (operatorStatusChange.equals(OperatorStatus.stop) || operatorStatusChange.equals(OperatorStatus
                        .pause) ||operatorStatusChange.equals(OperatorStatus.lock)){
                    throw new AcOperatorManagementException(AcExceptionCodes.CURRENT_STATUS_IS_NOT_ALLOWED_CHANGE,
                            wrap("退出状态只能修改为正常状态或挂起状态"));
                }
                break;
            case login:
                if (operatorStatusChange.equals(OperatorStatus.stop) || operatorStatusChange.equals(OperatorStatus.clear) ||operatorStatusChange.equals(OperatorStatus.lock)){
                    throw new AcOperatorManagementException(AcExceptionCodes.CURRENT_STATUS_IS_NOT_ALLOWED_CHANGE,
                            wrap("正常状态只能修改为退出状态或挂起状态"));
                }
                break;
            case clear:
                if (!(operatorStatusChange.equals(OperatorStatus.logout))){
                    throw new AcOperatorManagementException(AcExceptionCodes.CURRENT_STATUS_IS_NOT_ALLOWED_CHANGE,
                            wrap("注销状态只能修改为退出状态"));
                }
                break;
            case lock:
                if (operatorStatusChange.equals(OperatorStatus.stop) || operatorStatusChange.equals(OperatorStatus.login) ||operatorStatusChange.equals(OperatorStatus.pause)){
                    throw new AcOperatorManagementException(AcExceptionCodes.CURRENT_STATUS_IS_NOT_ALLOWED_CHANGE,
                            wrap("锁定状态只能修改为退出状态和注销状态"));
                }
                break;
            case pause:
                if (operatorStatusChange.equals(OperatorStatus.stop) || operatorStatusChange.equals(OperatorStatus.clear) ||operatorStatusChange.equals(OperatorStatus.lock)){
                    throw new AcOperatorManagementException(AcExceptionCodes.CURRENT_STATUS_IS_NOT_ALLOWED_CHANGE,
                            wrap("挂起状态只能修改为退出状态和正常状态"));
                }
                break;
            default:
                    throw new AcOperatorManagementException(AcExceptionCodes.CURRENT_STATUS_IS_NOT_ALLOWED_CHANGE,
                            wrap("该状态非操作员状态!"));
        }

        acOperator.setOperatorStatus(operatorStatusChange);
        updateById(acOperator);

        return acOperator;
    }

    /**
     * 查询所有操作员
     *
     * @return
     * @throws AcOperatorManagementException
     */
    @Override
    public List<AcOperator> queryAllOperator() throws AcOperatorManagementException {

        Wrapper<AcOperator> wrapper = new EntityWrapper<AcOperator>();

        return selectList(wrapper);
    }


    @Override
    public void moveOperator(String id) throws AcOperatorManagementException {

        try {

            //删除操作员与权限集（角色）对应关系数据
            Wrapper<AcOperatorRole> wrapperRole = new EntityWrapper<AcOperatorRole>();
            wrapperRole.eq(AcOperatorRole.COLUMN_GUID_OPERATOR,id);
            List<AcOperatorRole> listRole = acOperatorRoleService.selectList(wrapperRole);
            if (0 != listRole.size()){
                acOperatorRoleService.delete(wrapperRole);
            }

            //删除操作员特殊权限配置数据
            Wrapper<AcOperatorFunc> wrapperFunc = new EntityWrapper<AcOperatorFunc>();
            wrapperFunc.eq(AcOperatorFunc.COLUMN_GUID_OPERATOR,id);
            List<AcOperatorFunc> listFunc = acOperatorFuncService.selectList(wrapperFunc);
            if (0 != listFunc.size()){
                acOperatorFuncService.delete(wrapperFunc);
            }

            //删除操作员个性配置数据
            Wrapper<AcOperatorConfig> wrapperConfig = new EntityWrapper<AcOperatorConfig>();
            wrapperConfig.eq(AcOperatorConfig.COLUMN_GUID_OPERATOR,id);
            List<AcOperatorConfig> listConfig = acOperatorConfigService.selectList(wrapperConfig);
            if (0 != listConfig.size()){
                acOperatorConfigService.delete(wrapperConfig);
            }

            //删除操作员重组菜单数据
            Wrapper<AcOperatorMenu> wrapperMenu = new EntityWrapper<AcOperatorMenu>();
            wrapperMenu.eq(AcOperatorMenu.COLUMN_GUID_OPERATOR,id);
            List<AcOperatorMenu> listMenu = acOperatorMenuService.selectList(wrapperMenu);
            if (0 != listMenu.size()){
                acOperatorMenuService.delete(wrapperMenu);
            }

            //删除操作员快捷菜单数据
            Wrapper<AcOperatorShortcut> wrapperShort = new EntityWrapper<AcOperatorShortcut>();
            wrapperShort.eq(AcOperatorShortcut.COLUMN_GUID_OPERATOR,id);
            List<AcOperatorShortcut> listShort = acOperatorShortcutService.selectList(wrapperShort);
            if (0 != listShort.size()){
                acOperatorShortcutService.delete(wrapperShort);
            }

            //删除操作员身份数据
            Wrapper<AcOperatorIdentity> wrapperIdentity = new EntityWrapper<AcOperatorIdentity>();
            wrapperIdentity.eq(AcOperatorIdentity.COLUMN_GUID_OPERATOR,id);
            List<AcOperatorIdentity> listIdentity = acOperatorIdentityService.selectList(wrapperIdentity);
            if (0 != listIdentity.size()){
               for (AcOperatorIdentity acOperatorIdentity : listIdentity){
                   if (null != acOperatorIdentity){
                       acOperatorIdentityService.moveIdentity(acOperatorIdentity.getGuid());
                   }
               }
            }

            //删除操作员
            deleteById(id);

        }catch (Exception e){
            e.printStackTrace();
            throw new AcOperatorManagementException(AcExceptionCodes.FAILURE_WHEN_DELETE_AC_OPERATOR,wrap(e
                    .getMessage()));
        }

    }
}

