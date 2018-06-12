package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorConfigRequest;
import org.tis.tools.abf.module.ac.dao.AcOperatorConfigMapper;
import org.tis.tools.abf.module.ac.entity.AcAppConfig;
import org.tis.tools.abf.module.ac.entity.AcOperator;
import org.tis.tools.abf.module.ac.entity.AcOperatorConfig;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.service.IAcAppConfigService;
import org.tis.tools.abf.module.ac.service.IAcOperatorConfigService;
import org.tis.tools.abf.module.ac.service.IAcOperatorService;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * acOperatorConfig的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcOperatorConfigServiceImpl extends ServiceImpl<AcOperatorConfigMapper, AcOperatorConfig> implements IAcOperatorConfigService {

    @Autowired
    private IAcOperatorService acOperatorService;

    @Autowired
    private IAcAppConfigService acAppConfigService;


    @Override
    public void add(AcOperatorConfigRequest acOperatorConfigRequest) throws AcManagementException {

        //判断操作员是否存在
        String operatorGuid = acOperatorConfigRequest.getGuidOperator();
        AcOperator acOperator = acOperatorService.selectById(operatorGuid);
        if (acOperator == null){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_AC_OPERATOR,wrap("操作员ID对应的操作员不存在",
                    operatorGuid));
        }

        //判断功能是否存在
        String configGuid = acOperatorConfigRequest.getGuidConfig();
        AcAppConfig acAppConfig = acAppConfigService.selectById(configGuid);
        if (acAppConfig == null){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_APPCONFIG,wrap("配置ID对应的配置不存在",
                    configGuid));
        }

        //收集参数
        AcOperatorConfig acOperatorConfig = new AcOperatorConfig();
        acOperatorConfig.setGuidConfig(configGuid);
        acOperatorConfig.setGuidOperator(operatorGuid);
        acOperatorConfig.setConfigValue(acOperatorConfigRequest.getConfigValue());

        insert(acOperatorConfig);
    }

    @Override
    public AcOperatorConfig change(AcOperatorConfigRequest acOperatorConfigRequest) throws AcManagementException {

        //判断操作员是否存在
        String operatorGuid = acOperatorConfigRequest.getGuidOperator();
        AcOperator acOperator = acOperatorService.selectById(operatorGuid);
        if (acOperator == null){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_AC_OPERATOR,wrap("操作员ID对应的操作员不存在",
                    operatorGuid));
        }

        //判断功能是否存在
        String configGuid = acOperatorConfigRequest.getGuidConfig();
        AcAppConfig acAppConfig = acAppConfigService.selectById(configGuid);
        if (acAppConfig == null){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_APPCONFIG,wrap("配置ID对应的配置不存在",
                    configGuid));
        }

        //收集参数
        AcOperatorConfig acOperatorConfig = new AcOperatorConfig();
        acOperatorConfig.setGuid(acOperatorConfigRequest.getGuid());
        acOperatorConfig.setGuidConfig(configGuid);
        acOperatorConfig.setGuidOperator(operatorGuid);
        acOperatorConfig.setConfigValue(acOperatorConfigRequest.getConfigValue());

        updateById(acOperatorConfig);

        return acOperatorConfig;
    }
}

