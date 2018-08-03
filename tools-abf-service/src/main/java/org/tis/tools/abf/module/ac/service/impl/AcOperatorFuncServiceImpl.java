package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.controller.request.*;
import org.tis.tools.abf.module.ac.dao.AcOperatorFuncMapper;
import org.tis.tools.abf.module.ac.entity.*;
import org.tis.tools.abf.module.ac.entity.enums.FuncType;
import org.tis.tools.abf.module.ac.entity.vo.OperatorRoleAdd;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.service.IAcAppService;
import org.tis.tools.abf.module.ac.service.IAcFuncService;
import org.tis.tools.abf.module.ac.service.IAcOperatorFuncService;
import org.tis.tools.abf.module.ac.service.IAcOperatorService;
import org.tis.tools.core.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * acOperatorFunc的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcOperatorFuncServiceImpl extends ServiceImpl<AcOperatorFuncMapper, AcOperatorFunc> implements IAcOperatorFuncService {

    @Autowired
    private IAcOperatorService acOperatorService;

    @Autowired
    private IAcFuncService acFuncService;

    @Autowired
    private IAcAppService acAppService;

    @Override
    public void add(AcOperatorFuncRequest acOperatorFuncRequestc) throws AcManagementException {

        //判断操作员是否存在
        String operatorGuid = acOperatorFuncRequestc.getGuidOperator();
        AcOperator acOperator = acOperatorService.selectById(operatorGuid);
        if (acOperator == null){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_AC_OPERATOR,wrap("操作员ID对应的操作员不存在",
                    operatorGuid));
        }

        //判断功能是否存在
        String funcGuid = acOperatorFuncRequestc.getGuidFunc();
        AcFunc acFunc = acFuncService.selectById(funcGuid);
        if (acFunc == null){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_FUNC,wrap("功能ID对应的功能不存在",
                    funcGuid));
        }

        //判断应用是否存在
        String appGuid = acOperatorFuncRequestc.getGuidApp();
        if (!StringUtil.isEmpty(appGuid)){
            AcApp acApp = acAppService.selectById(appGuid);
            if (acApp == null){
                throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_APP,wrap("应用ID对应的应用不存在",
                        appGuid));
            }
        }

        //收集参数
        AcOperatorFunc acOperatorFunc = new AcOperatorFunc();
        acOperatorFunc.setGuidFunc(funcGuid);
        acOperatorFunc.setGuidOperator(operatorGuid);
        acOperatorFunc.setGuidApp(appGuid);
        acOperatorFunc.setAuthType(acOperatorFuncRequestc.getAuthType());
        acOperatorFunc.setStartDate(acOperatorFuncRequestc.getStartDate());
        acOperatorFunc.setEndDate(acOperatorFuncRequestc.getEndDate());

        //新增
        insert(acOperatorFunc);

    }


    @Override
    public AcOperatorFunc change(AcOperatorFuncRequest acOperatorFuncRequestc) throws AcManagementException {
        //判断操作员是否存在
        String operatorGuid = acOperatorFuncRequestc.getGuidOperator();
        AcOperator acOperator = acOperatorService.selectById(operatorGuid);
        if (acOperator == null){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_AC_OPERATOR,wrap("操作员ID对应的操作员不存在",
                    operatorGuid));
        }

        //判断功能是否存在
        String funcGuid = acOperatorFuncRequestc.getGuidFunc();
        AcFunc acFunc = acFuncService.selectById(funcGuid);
        if (acFunc == null){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_FUNC,wrap("功能ID对应的功能不存在",
                    funcGuid));
        }

        //判断应用是否存在
        String appGuid = acOperatorFuncRequestc.getGuidApp();
        if (!StringUtil.isEmpty(appGuid)){
            AcApp acApp = acAppService.selectById(appGuid);
            if (acApp == null){
                throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_APP,wrap("应用ID对应的应用不存在",
                        appGuid));
            }
        }

        //收集参数
        AcOperatorFunc acOperatorFunc = new AcOperatorFunc();
        acOperatorFunc.setGuid(acOperatorFuncRequestc.getGuid());
        acOperatorFunc.setGuidFunc(funcGuid);
        acOperatorFunc.setGuidOperator(operatorGuid);
        acOperatorFunc.setGuidApp(appGuid);
        acOperatorFunc.setAuthType(acOperatorFuncRequestc.getAuthType());
        acOperatorFunc.setStartDate(acOperatorFuncRequestc.getStartDate());
        acOperatorFunc.setEndDate(acOperatorFuncRequestc.getEndDate());

        //更新
        updateById(acOperatorFunc);
        return acOperatorFunc;
    }

    /**
     * 查询所有应用和操作员下已有应用
     *
     * @param operatorId
     * @return
     * @throws AcManagementException
     */
    @Override
    public AcRoleFuncQueRequest<AcApp> queryAppByOperator(String operatorId) throws AcManagementException {

        //查询所有的应用
        Wrapper<AcApp> appWrapper = new EntityWrapper<AcApp>();
        List<AcApp> allApps = acAppService.selectList(appWrapper);

        //查询已有应用
        List<AcApp> oldApps = this.baseMapper.queryAppByOperator(operatorId);

        AcRoleFuncQueRequest<AcApp> queRequest = new AcRoleFuncQueRequest<AcApp>();
        queRequest.setAllData(allApps);
        queRequest.setOldData(oldApps);

        return queRequest;
    }

    /**
     * 查询应用下的所有功能 和 操作员和应用下的所有功能
     *
     * @param conditionRequest
     * @return
     * @throws AcManagementException
     */
    @Override
    public AcRoleFuncQueRequest<AcFunc> queryFuncByOperator(AcOperatorFuncQueConditionRequest conditionRequest) throws AcManagementException {
        //判断应用id是否为空
        if (StringUtil.isEmpty(conditionRequest.getAppId())){
            throw new AcManagementException(AcExceptionCodes.APPID_ISNULL_WHRN_QUERY_ROLE_FUNC,wrap("应用的id不能为空"));
        }

        //查询应用下的所有功能
        Wrapper<AcFunc> funcWrapper = new EntityWrapper<AcFunc>();
        funcWrapper.eq(AcFunc.COLUMN_GUID_APP,conditionRequest.getAppId());
        funcWrapper.eq(AcFunc.COLUMN_FUNC_TYPE, FuncType.FUNCTION);

        List<AcFunc> allFuncs = acFuncService.selectList(funcWrapper);

        //查询操作员和应用下的功能
        List<AcFunc> olFuncs = this.baseMapper.queryFuncByOperator(conditionRequest.getOperatorId(),conditionRequest.getAppId());

        AcRoleFuncQueRequest<AcFunc> queRequest = new AcRoleFuncQueRequest<AcFunc>();
        queRequest.setAllData(allFuncs);
        queRequest.setOldData(olFuncs);

        return queRequest;
    }

    /**
     * 查询功能下的所有行为 和 操作员和功能下的所有行为
     *
     * @param conditionRequest
     * @return
     * @throws AcManagementException
     */
    @Override
    public AcRoleFuncQueRequest<AcFunc> queryBehaveByOperator(AcOperatorFuncQueConditionRequest conditionRequest) throws AcManagementException {
        //判断应用id是否为空
        if (StringUtil.isEmpty(conditionRequest.getAppId())){
            throw new AcManagementException(AcExceptionCodes.APPID_ISNULL_WHRN_QUERY_ROLE_FUNC,wrap("应用的id不能为空"));
        }

        //判断功能id是否为空
        if (StringUtil.isEmpty(conditionRequest.getFuncId())){
            throw new AcManagementException(AcExceptionCodes.FUNCID_ISNULL_WHRN_QUERY_ROLE_FUNC,wrap("功能的id不能为空"));
        }

        //查询功能下的所有行为
        Wrapper<AcFunc> funcWrapper = new EntityWrapper<AcFunc>();
        funcWrapper.eq(AcFunc.COLUMN_GUID_APP,conditionRequest.getAppId());
        funcWrapper.eq(AcFunc.COLUMN_GUID_FUNC,conditionRequest.getFuncId());
        funcWrapper.eq(AcFunc.COLUMN_FUNC_TYPE,FuncType.BEHAVE);

        List<AcFunc> allBehaves= acFuncService.selectList(funcWrapper);

        //查询功能下已有的行为
        List<AcFunc> oldBehaves = this.baseMapper.queryBehaveByOperator(conditionRequest.getOperatorId(),conditionRequest
                .getAppId(),conditionRequest.getFuncId());

        AcRoleFuncQueRequest<AcFunc> queRequest = new AcRoleFuncQueRequest<AcFunc>();
        queRequest.setOldData(oldBehaves);
        queRequest.setAllData(allBehaves);

        return queRequest;
    }

    /**
     * 批量新增操作员_应用数据
     *
     * @throws AcManagementException
     */
    @Override
    public void batchAdd(AcOperatorBatchAddRequest batchAddRequest) throws AcManagementException {
//待处理的前端数据
        List<AcOperatorFunc> listsNew = findAllData(batchAddRequest);

        List<AcOperatorFunc> listNewAll = new ArrayList<AcOperatorFunc>();
        //添加新数据
        if (null != listsNew){
            for (AcOperatorFunc acOperatorFunc : listsNew){
                if (null != acOperatorFunc){
                    Wrapper<AcOperatorFunc> funcAddWrapper = new EntityWrapper<AcOperatorFunc>();
                    funcAddWrapper.eq(AcOperatorFunc.COLUMN_GUID_OPERATOR,batchAddRequest.getOperatorId());
                    funcAddWrapper.eq(AcOperatorFunc.COLUMN_GUID_FUNC,acOperatorFunc.getGuidFunc());
                    funcAddWrapper.eq(AcOperatorFunc.COLUMN_GUID_APP,acOperatorFunc.getGuidApp());

                    AcOperatorFunc acOperatorFunc1 = selectOne(funcAddWrapper);
                    if (null == acOperatorFunc1){
                        AcOperatorFunc acOperatorFunc2 = new AcOperatorFunc();
                        acOperatorFunc2.setGuidOperator(batchAddRequest.getOperatorId());
                        acOperatorFunc2.setGuidFunc(acOperatorFunc.getGuidFunc());
                        acOperatorFunc2.setGuidApp(acOperatorFunc.getGuidApp());

                        insert(acOperatorFunc2);
                        AcOperatorFunc acOperatorFunc3 = selectOne(funcAddWrapper);
                        listNewAll.add(acOperatorFunc3);
                    }else {
                        listNewAll.add(acOperatorFunc1);
                    }
                }
            }
        }

        //查询出该角色下的所有 角色-功能 数据
        Wrapper<AcOperatorFunc> wrapper = new EntityWrapper<AcOperatorFunc>();
        wrapper.eq(AcOperatorFunc.COLUMN_GUID_OPERATOR,batchAddRequest.getOperatorId());

        List<AcOperatorFunc> listOld = selectList(wrapper);

        //找出原数据有,现数据中没有的数据,用于删除使用
        listOld.removeAll(listNewAll);
        //删除多于的数据
        if (null != listOld){
            for (AcOperatorFunc acOperatorFunc : listOld){
                if (null != acOperatorFunc){
                    deleteById(acOperatorFunc.getGuid());
                }
            }
        }

    }

    /** 整理出前端传来的数据 */
    private List<AcOperatorFunc> findAllData(AcOperatorBatchAddRequest batchAddRequest){
        //查询该角色现有数据
        List<AcOperatorFunc> listsNew = new ArrayList<AcOperatorFunc>();
        if (null != batchAddRequest.getBatchData()){
            //遍历map,获取应用id
            for(String  appId : batchAddRequest.getBatchData().keySet()){
                if (!StringUtil.isEmpty(appId)){
                    //获得
                    OperatorRoleAdd operatorRoleAdd = batchAddRequest.getBatchData().get(appId);
                    if (null != operatorRoleAdd){
                        for (String funcId : operatorRoleAdd.getFuncData().keySet()){
                            if (!StringUtil.isEmpty(funcId)){
                                AcOperatorFunc acOperatorFunc1 = new AcOperatorFunc();
                                for (String behaveId : operatorRoleAdd.getFuncData().get(funcId)){
                                    if (!StringUtil.isEmpty(behaveId)){
                                        AcOperatorFunc acOperatorFunc2 = new AcOperatorFunc();
                                        acOperatorFunc2.setGuidApp(appId);
                                        acOperatorFunc2.setGuidOperator(batchAddRequest.getOperatorId());
                                        acOperatorFunc2.setGuidFunc(behaveId);

                                        listsNew.add(acOperatorFunc2);
                                    }
                                }

                                acOperatorFunc1.setGuidApp(appId);
                                acOperatorFunc1.setGuidOperator(batchAddRequest.getOperatorId());
                                acOperatorFunc1.setGuidFunc(funcId);
                                listsNew.add(acOperatorFunc1);
                            }
                        }
                    }
                }
            }
        }
        return listsNew;
    }
}

