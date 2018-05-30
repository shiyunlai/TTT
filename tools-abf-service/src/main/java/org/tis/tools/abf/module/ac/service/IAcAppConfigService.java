package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.entity.AcAppConfig;
import org.tis.tools.abf.module.ac.exception.AcManagementException;

/**
 * acAppConfig的Service接口类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IAcAppConfigService extends IService<AcAppConfig>  {

    /**
     * 新增应用个性化配置
     * @param guidApp     应用guid
     * @param configType  配置类型
     * @param configName  配置名
     * @param configDict  配置值字典
     * @param configStyle 配置类型
     * @param configValue 默认配置值
     * @param enabled     是否启用
     * @param displayOrder 显示顺序
     * @param configDesc  配置描述说明
     * @return
     */
    AcAppConfig createRootAppConfig(String guidApp, String configType, String configName, String configDict, String configStyle, String configValue, String enabled, String displayOrder, String configDesc) throws AcManagementException;

    /**
     *
     * @param guid
     * @param guidApp       应用guid
     * @param configType    配置类型
     * @param configName    配置名
     * @param configDict    配置值字典
     * @param configStyle   配置类型
     * @param configValue   默认配置值
     * @param enabled       是否启用
     * @param displayOrder  显示顺序
     * @param configDesc    配置描述说明
     * @return
     */
    AcAppConfig changeById(String guid,String guidApp,String configType,String configName,String configDict,String configStyle,String configValue,String enabled,String displayOrder,String configDesc) throws AcManagementException ;

    /**
     * 分页查询应用参数
     * @param page
     * @param wrapper
     * @param id
     * @return
     * @throws AcManagementException
     */
    Page<AcAppConfig> queryPageById(Page<AcAppConfig> page , Wrapper<AcAppConfig> wrapper ,String id) throws
            AcManagementException;

}

