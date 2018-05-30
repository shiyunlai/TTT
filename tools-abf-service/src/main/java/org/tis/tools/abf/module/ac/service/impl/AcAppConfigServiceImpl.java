package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.dao.AcAppConfigMapper;
import org.tis.tools.abf.module.ac.entity.AcAppConfig;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.service.IAcAppConfigService;

import java.math.BigDecimal;

import static org.tis.tools.core.utils.BasicUtil.wrap;

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
    public AcAppConfig createRootAppConfig(String guidApp, String configType, String configName, String configDict, String configStyle, String configValue, String enabled, String displayOrder, String configDesc) throws AcManagementException {
        AcAppConfig acAppConfig = new AcAppConfig();


        try {
            //转换displayOrder类型
            BigDecimal displayOrderNew;
            if ("".equals(displayOrder) || null == displayOrder){
                displayOrderNew = null;
            }else {
                displayOrderNew = BigDecimal.valueOf(Double.valueOf(displayOrder));
            }
            //收集参数
            acAppConfig.setGuidApp(guidApp);
            acAppConfig.setConfigType(configType);
            acAppConfig.setConfigName(configName);
            acAppConfig.setConfigDict(configDict);
            acAppConfig.setConfigStyle(configStyle);
            acAppConfig.setConfigValue(configValue);
            acAppConfig.setEnabled(enabled);
            acAppConfig.setDisplayOrder(displayOrderNew);
            acAppConfig.setConfigDesc(configDesc);

            acAppConfigService.insert(acAppConfig);
        }catch (AcManagementException ae){
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_CREATE_AC_APPCONFIG,
                    wrap(e.getMessage())
            );
        }

        return acAppConfig;
    }


    /**
     * 修改个性配置
     */
    @Override
    public AcAppConfig changeById(String guid, String guidApp, String configType, String configName, String configDict, String configStyle, String configValue, String enabled, String displayOrder, String configDesc) throws AcManagementException {

        AcAppConfig acAppConfig = new AcAppConfig();

        try {
            //转换displayOrder类型
            BigDecimal displayOrderNew;
            if ("".equals(displayOrder) || null == displayOrder){
                displayOrderNew = null;
            }else {
                displayOrderNew = BigDecimal.valueOf(Double.valueOf(displayOrder));
            }

            //收录参数
            acAppConfig.setGuid(guid);
            acAppConfig.setGuidApp(guidApp);
            acAppConfig.setConfigType(configType);
            acAppConfig.setConfigName(configName);
            acAppConfig.setConfigDict(configDict);
            acAppConfig.setConfigStyle(configStyle);
            acAppConfig.setConfigValue(configValue);
            acAppConfig.setEnabled(enabled);
            acAppConfig.setDisplayOrder(displayOrderNew);
            acAppConfig.setConfigDesc(configDesc);

            acAppConfigService.updateById(acAppConfig);
        }catch (AcManagementException ae){
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_UPDATE_AC_APPCONFIG,
                    wrap(e.getMessage())
            );
        }
        return acAppConfig;
    }

    @Override
    public Page<AcAppConfig> queryPageById(Page<AcAppConfig> page, Wrapper<AcAppConfig> wrapper, String id) throws AcManagementException {

        if (null == wrapper){
            wrapper = new EntityWrapper<AcAppConfig>();
        }

        wrapper.eq(AcAppConfig.COLUMN_GUID_APP,id);
        Page<AcAppConfig> pageConfig = selectPage(page,wrapper);

        return pageConfig;
    }
}

