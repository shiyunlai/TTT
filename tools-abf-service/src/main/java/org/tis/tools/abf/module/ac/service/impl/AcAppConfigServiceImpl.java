package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.abf.module.ac.service.IAcAppConfigService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.dao.AcAppConfigMapper;
import org.tis.tools.abf.module.ac.entity.AcAppConfig;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.common.entity.enums.YON;

import java.math.BigDecimal;
import java.util.List;

/**
 * acAppConfig的Service接口实现类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcAppConfigServiceImpl extends ServiceImpl<AcAppConfigMapper, AcAppConfig> implements IAcAppConfigService {

    @Autowired
    IAcAppConfigService acAppConfigService;

    /**
     * 新增应用个性化配置
     */
    @Override
    public AcAppConfig createRootAppConfig(String guidApp, String configType, String configName, String configDict, String configStyle, String configValue, String
            enabled, BigDecimal displayOrder, String configDesc) {
        AcAppConfig acAppConfig = new AcAppConfig();

        try {
            //收集参数
            acAppConfig.setGuidApp(guidApp);
            acAppConfig.setConfigType(configType);
            acAppConfig.setConfigName(configName);
            acAppConfig.setConfigDict(configDict);
            acAppConfig.setConfigStyle(configStyle);
            acAppConfig.setConfigValue(configValue);
            acAppConfig.setEnabled(enabled);
            acAppConfig.setDisplayOrder(displayOrder);
            acAppConfig.setConfigDesc(configDesc);

            acAppConfigService.insert(acAppConfig);
        }catch (Exception exception){
            throw exception;
        }

        return acAppConfig;
    }

    /**
     * 分页查询
     */
    @Override
    public Page<AcAppConfig> queryByPage(Page<AcAppConfig> page, Wrapper<AcAppConfig> wrapper) {

        Page<AcAppConfig> acAppConfigPage = null ;

        acAppConfigPage = acAppConfigService.selectPage(page,wrapper);

        return acAppConfigPage;
    }

    /**
     * 修改个性配置
     */
    @Override
    public AcAppConfig changeById(String guid, String guidApp, String configType, String configName, String configDict, String configStyle, String configValue, String enabled, BigDecimal displayOrder, String configDesc) {

        AcAppConfig acAppConfig = new AcAppConfig();

        try {
            //收录参数
            acAppConfig.setGuid(guid);
            acAppConfig.setGuidApp(guidApp);
            acAppConfig.setConfigType(configType);
            acAppConfig.setConfigName(configName);
            acAppConfig.setConfigDict(configDict);
            acAppConfig.setConfigStyle(configStyle);
            acAppConfig.setConfigValue(configValue);
            acAppConfig.setEnabled(enabled);
            acAppConfig.setDisplayOrder(displayOrder);
            acAppConfig.setConfigDesc(configDesc);

            acAppConfigService.updateById(acAppConfig);
        }catch (Exception exception){
            throw exception;
        }

        return acAppConfig;
    }
}

