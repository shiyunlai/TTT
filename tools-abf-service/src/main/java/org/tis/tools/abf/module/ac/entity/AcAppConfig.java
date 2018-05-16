package org.tis.tools.abf.module.ac.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * acAppConfig应用个性化配置项
 * 
 * @author Auto Generate Tools
 * @date 2018/05/16
 */
@Data
@TableName("ac_app_config")
public class AcAppConfig implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "个性化配置";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidApp对应表字段
     */
    public static final String COLUMN_GUID_APP = "guid_app";

    /**
     * configType对应表字段
     */
    public static final String COLUMN_CONFIG_TYPE = "config_type";

    /**
     * configName对应表字段
     */
    public static final String COLUMN_CONFIG_NAME = "config_name";

    /**
     * configDict对应表字段
     */
    public static final String COLUMN_CONFIG_DICT = "config_dict";

    /**
     * configStyle对应表字段
     */
    public static final String COLUMN_CONFIG_STYLE = "config_style";

    /**
     * configValue对应表字段
     */
    public static final String COLUMN_CONFIG_VALUE = "config_value";

    /**
     * enabled对应表字段
     */
    public static final String COLUMN_ENABLED = "enabled";

    /**
     * displayOrder对应表字段
     */
    public static final String COLUMN_DISPLAY_ORDER = "display_order";

    /**
     * configDesc对应表字段
     */
    public static final String COLUMN_CONFIG_DESC = "config_desc";

    /**
     * guid逻辑名
     */
    public static final String NAME_GUID = "数据主键";

    /**
     * guidApp逻辑名
     */
    public static final String NAME_GUID_APP = "应用GUID";

    /**
     * configType逻辑名
     */
    public static final String NAME_CONFIG_TYPE = "配置类型";

    /**
     * configName逻辑名
     */
    public static final String NAME_CONFIG_NAME = "配置名";

    /**
     * configDict逻辑名
     */
    public static final String NAME_CONFIG_DICT = "配置值字典";

    /**
     * configStyle逻辑名
     */
    public static final String NAME_CONFIG_STYLE = "配置风格";

    /**
     * configValue逻辑名
     */
    public static final String NAME_CONFIG_VALUE = "默认配置值";

    /**
     * enabled逻辑名
     */
    public static final String NAME_ENABLED = "是否启用";

    /**
     * displayOrder逻辑名
     */
    public static final String NAME_DISPLAY_ORDER = "显示顺序";

    /**
     * configDesc逻辑名
     */
    public static final String NAME_CONFIG_DESC = "配置描述说明";

    /**
     * 数据主键
     */
    @TableId
    private String guid;

    /**
     * 应用GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidApp;

    /**
     * 配置类型
     */
    private String configType;

    /**
     * 配置名
     */
    private String configName;

    /**
     * 配置值字典
     */
    private String configDict;

    /**
     * 配置风格
     */
    private String configStyle;

    /**
     * 默认配置值
     */
    private String configValue;

    /**
     * 是否启用
     */
    private String enabled;

    /**
     * 显示顺序:所在层次内的展示顺序
     */
    private BigDecimal displayOrder;

    /**
     * 配置描述说明
     */
    private String configDesc;

}

