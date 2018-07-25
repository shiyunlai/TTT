package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.dao.AcFuncMapper;
import org.tis.tools.abf.module.ac.entity.AcFunc;
import org.tis.tools.abf.module.ac.entity.AcFuncAttr;
import org.tis.tools.abf.module.ac.entity.AcOperatorFunc;
import org.tis.tools.abf.module.ac.entity.AcRoleFunc;
import org.tis.tools.abf.module.ac.entity.enums.FuncType;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.service.IAcFuncAttrService;
import org.tis.tools.abf.module.ac.service.IAcFuncService;
import org.tis.tools.abf.module.ac.service.IAcOperatorFuncService;
import org.tis.tools.abf.module.ac.service.IAcRoleFuncService;
import org.tis.tools.abf.module.common.entity.enums.YON;

import java.math.BigDecimal;
import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * acFunc的Service接口实现类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcFuncServiceImpl extends ServiceImpl<AcFuncMapper, AcFunc> implements IAcFuncService {

    @Autowired
    IAcFuncService acFuncService;

    @Autowired
    private IAcFuncAttrService acFuncAttrService;

    @Autowired
    private IAcRoleFuncService acRoleFuncService;

    @Autowired
    private IAcOperatorFuncService acOperatorFuncService;


    /**
     * 新增功能行为
     */
    @Override
    public AcFunc creatFunc(String guidApp, FuncType funcType, String funcCode, String funcName, String displayOrder, String funcDesc, String guidFunc,YON isopen,YON ischeck) throws AcManagementException {

        AcFunc acFunc = new AcFunc();

       if (null == isopen){
           isopen = YON.YES;
       }

       if (null == ischeck){
           ischeck = YON.YES;
       }

        try{
            BigDecimal displayOrderNew =  BigDecimal.valueOf(Double.valueOf(displayOrder));

            //收集参数
            acFunc.setGuidApp(guidApp);
            acFunc.setFuncCode(funcCode);
            acFunc.setFuncType(funcType);
            acFunc.setFuncName(funcName);
            acFunc.setDisplayOrder(displayOrderNew);
            acFunc.setFuncDesc(funcDesc);
            acFunc.setGuidFunc(guidFunc);

            //默认为需要验证
            acFunc.setIscheck(ischeck);
            //默认为应用启用
            acFunc.setIsopen(isopen);

            //新增
            acFuncService.insert(acFunc);
        }catch (AcManagementException ae){
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_CREATE_AC_FUNC,
                    wrap(e.getMessage())
            );
        }

        return acFunc;
    }

    /**
     * 修改功能行为
     */
    @Override
    public AcFunc changeFunc(String guid, String guidApp, FuncType funcType, String funcCode, String funcName, String funcDesc, YON isopen, YON ischeck, String displayOrder, String guidFunc) throws AcManagementException {

        AcFunc acFunc = new AcFunc();

        try{
            BigDecimal disPlayOrderNew = BigDecimal.valueOf(Double.valueOf(displayOrder));

            acFunc.setGuid(guid);
            acFunc.setGuidApp(guidApp);
            acFunc.setFuncType(funcType);
            acFunc.setFuncCode(funcCode);
            acFunc.setFuncName(funcName);
            acFunc.setFuncDesc(funcDesc);
            acFunc.setGuidFunc(guidFunc);
            acFunc.setDisplayOrder(disPlayOrderNew);
            acFunc.setIsopen(isopen);
            acFunc.setIscheck(ischeck);

            //修改
            acFuncService.updateById(acFunc);
        }catch (AcManagementException ae){
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_UPDATE_AC_FUNC,
                    wrap(e.getMessage())
            );
        }
        return acFunc;
    }

    /**
     * 查询对应应用下的功能
     */
    @Override
    public Page<AcFunc> queryPageById(Page<AcFunc> page, Wrapper<AcFunc> wrapper, String id) throws AcManagementException {

        Page<AcFunc> pagefunc = null;

        if (null == wrapper){
            wrapper = new EntityWrapper<AcFunc>();
        }

        try {
            wrapper.eq(AcFunc.COLUMN_GUID_APP,id);
            pagefunc = acFuncService.selectPage(page,wrapper);
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_FUNC,wrap(e.getMessage()));
        }

        return pagefunc;
    }


    @Override
    public List<AcFunc> queryAll() throws AcManagementException {
        Wrapper<AcFunc> wrapper = new EntityWrapper<AcFunc>();
        return selectList(wrapper);
    }


    @Override
    public void moveFunc(String id) throws AcManagementException {

        try {

            //查询出功能属性
            Wrapper<AcFuncAttr> wrapperAttr = new EntityWrapper<AcFuncAttr>();
            wrapperAttr.eq(AcFuncAttr.COLUMN_GUID_FUNC,id);
            List<AcFuncAttr> listAttr = acFuncAttrService.selectList(wrapperAttr);
            if (0 != listAttr.size()){
                //删除功能属性
                acFuncAttrService.delete(wrapperAttr);
            }

            //查询出权限集(角色)功能对应关系信息
            Wrapper<AcRoleFunc> wrapperRole = new EntityWrapper<AcRoleFunc>();
            wrapperRole.eq(AcRoleFunc.COLUMN_GUID_FUNC,id);
            List<AcRoleFunc> listRole = acRoleFuncService.selectList(wrapperRole);
            if (0 != listRole.size()){
                //删除权限集(角色)功能数据
                acRoleFuncService.delete(wrapperRole);
            }

            //查询出操作员特殊权限配置信息
            Wrapper<AcOperatorFunc> wrapperOper = new EntityWrapper<AcOperatorFunc>();
            wrapperOper.eq(AcOperatorFunc.COLUMN_GUID_FUNC,id);
            List<AcOperatorFunc> listOper = acOperatorFuncService.selectList(wrapperOper);
            if (0 != listOper.size()){
                //删除操作员特殊权限配置数据
                acOperatorFuncService.delete(wrapperOper);
            }

            //删除功能
            deleteById(id);

        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_DELETE_AC_FUNC,wrap(e.getMessage()));
        }
    }


    @Override
    public List<AcFunc> queryFuncTreeByApp(String appId) throws AcManagementException {

        Wrapper<AcFunc> funcWrapper = new EntityWrapper<AcFunc>();
        funcWrapper.eq(AcFunc.COLUMN_GUID_APP,appId);
        funcWrapper.isNull(AcFunc.COLUMN_GUID_FUNC);
        funcWrapper.eq(AcFunc.COLUMN_FUNC_TYPE,FuncType.FUNCTION);

        List<AcFunc> funcList = selectList(funcWrapper);


        return funcList;
    }

    /**
     * 根据应用和父功能下的子功能查询功能列表
     *
     * @param appId
     * @param funcId
     * @return
     * @throws AcManagementException
     */
    @Override
    public List<AcFunc> queryFuncTreeByAppFunc(String appId, String funcId) throws AcManagementException {

        Wrapper<AcFunc> wrapper = new EntityWrapper<AcFunc>();

        wrapper.eq(AcFunc.COLUMN_GUID_APP,appId);
        wrapper.eq(AcFunc.COLUMN_GUID_FUNC,funcId);
        wrapper.eq(AcFunc.COLUMN_FUNC_TYPE,FuncType.FUNCTION);

        List<AcFunc> list = selectList(wrapper);

        return list;
    }

    /**
     * 根据应用和功能查询行为列表
     *
     * @param appId
     * @param funcId
     * @return
     * @throws AcManagementException
     */
    @Override
    public List<AcFunc> queryBehaveTreeByAppFunc(String appId, String funcId) throws AcManagementException {
        Wrapper<AcFunc> wrapper = new EntityWrapper<AcFunc>();

        wrapper.eq(AcFunc.COLUMN_GUID_APP,appId);
        wrapper.eq(AcFunc.COLUMN_GUID_FUNC,funcId);
        wrapper.eq(AcFunc.COLUMN_FUNC_TYPE,FuncType.BEHAVE);

        List<AcFunc> list = selectList(wrapper);

        return list;
    }
}

