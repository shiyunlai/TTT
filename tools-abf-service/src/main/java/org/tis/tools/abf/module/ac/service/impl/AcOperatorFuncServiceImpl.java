package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorFuncRequest;
import org.tis.tools.abf.module.ac.dao.AcOperatorFuncMapper;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.ac.entity.AcFunc;
import org.tis.tools.abf.module.ac.entity.AcOperator;
import org.tis.tools.abf.module.ac.entity.AcOperatorFunc;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.service.IAcAppService;
import org.tis.tools.abf.module.ac.service.IAcFuncService;
import org.tis.tools.abf.module.ac.service.IAcOperatorFuncService;
import org.tis.tools.abf.module.ac.service.IAcOperatorService;

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
        if ((!"".equals(appGuid)) || null != appGuid){
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
        if ((!"".equals(appGuid)) || null != appGuid){
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
}

