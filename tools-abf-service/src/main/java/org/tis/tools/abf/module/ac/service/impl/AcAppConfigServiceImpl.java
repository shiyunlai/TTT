package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.dao.AcAppConfigMapper;
import org.tis.tools.abf.module.ac.entity.AcAppConfig;
import org.tis.tools.abf.module.ac.entity.AcOperatorConfig;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.service.IAcAppConfigService;
import org.tis.tools.abf.module.ac.service.IAcOperatorConfigService;

import java.math.BigDecimal;
import java.util.List;

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

    @Autowired
    private IAcOperatorConfigService acOperatorConfigService;

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
    public void moveAppConfig(String id) throws AcManagementException {

        try {
            //根据个性化配置ID查询出有关 操作员个性配置的信息
            Wrapper<AcOperatorConfig> wrapper = new EntityWrapper<AcOperatorConfig>();
            wrapper.eq(AcOperatorConfig.COLUMN_GUID_CONFIG,id);
            //查询出所有数据
            List<AcOperatorConfig> list = acOperatorConfigService.selectList(wrapper);
            if (0 != list.size()){
                //删除掉相关数据
                acOperatorConfigService.delete(wrapper);
            }

            //删除个性化配置
            deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_DELETE_AC_APPCONFIG,wrap(e.getMessage()));
        }
    }
}

