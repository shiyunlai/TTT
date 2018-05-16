package org.tis.tools.abf.module.ac.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * acOperatorConfig操作员个性化配置
 * 如颜色配置
 *     登录风格
 *     是否使用重组菜单
 *     默认身份
 *     等
 * 
 * “操作员＋应用系统”，将配置按应用系统进行区分。
 * 
 * @author Auto Generate Tools
 * @date 2018/05/16
 */
@Data
@TableName("ac_operator_config")
public class AcOperatorConfig implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "操作员个性配置";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidOperator对应表字段
     */
    public static final String COLUMN_GUID_OPERATOR = "guid_operator";

    /**
     * guidConfig对应表字段
     */
    public static final String COLUMN_GUID_CONFIG = "guid_config";

    /**
     * configValue对应表字段
     */
    public static final String COLUMN_CONFIG_VALUE = "config_value";

    /**
     * guid逻辑名
     */
    public static final String NAME_GUID = "数据主键";

    /**
     * guidOperator逻辑名
     */
    public static final String NAME_GUID_OPERATOR = "操作员GUID";

    /**
     * guidConfig逻辑名
     */
    public static final String NAME_GUID_CONFIG = "配置GUID";

    /**
     * configValue逻辑名
     */
    public static final String NAME_CONFIG_VALUE = "配置值";

    /**
     * 数据主键:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    @TableId
    private String guid;

    /**
     * 操作员GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidOperator;

    /**
     * 配置GUID
     */
    private String guidConfig;

    /**
     * 配置值
     */
    private String configValue;

}

