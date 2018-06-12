package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorStatusRequest;
import org.tis.tools.abf.module.ac.dao.AcOperatorMapper;
import org.tis.tools.abf.module.ac.entity.AcOperator;
import org.tis.tools.abf.module.ac.entity.enums.OperatorStatus;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcOperatorManagementException;
import org.tis.tools.abf.module.ac.service.IAcOperatorService;
import org.tis.tools.core.exception.i18.ExceptionCodes;

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

        //收集参数
        AcOperator acOperator = new AcOperator();
        acOperator.setUserId(request.getUserId());
        acOperator.setPassword(request.getPassword());
        acOperator.setOperatorName(request.getOperatorName());
        acOperator.setOperatorStatus(request.getOperatorStatus());
        acOperator.setInvalDate(request.getInvalDate());
        acOperator.setAuthMode(request.getAuthMode());
        acOperator.setLockLimit(request.getLockLimit());
        acOperator.setErrCount(request.getErrCount());
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
            throw new AcOperatorManagementException(ExceptionCodes.FAILURE_WHEN_INSERT,wrap("ACOPERATOR",e));
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
            throw new AcOperatorManagementException(ExceptionCodes.FAILURE_WHEN_UPDATE,wrap("ACOPERATOR",e));
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
}

