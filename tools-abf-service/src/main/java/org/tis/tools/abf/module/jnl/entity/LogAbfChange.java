package org.tis.tools.abf.module.jnl.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * logAbfChange
 * 
 * @author Auto Generate Tools
 * @date 2018/05/16
 */
@Data
@TableName("log_abf_change")
public class LogAbfChange implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "记录日志差异值";

    /**
     * guidData对应表字段
     */
    public static final String COLUMN_GUID_DATA = "guid_data";

    /**
     * physicalName对应表字段
     */
    public static final String COLUMN_PHYSICAL_NAME = "physical_name";

    /**
     * logicName对应表字段
     */
    public static final String COLUMN_LOGIC_NAME = "logic_name";

    /**
     * oldValue对应表字段
     */
    public static final String COLUMN_OLD_VALUE = "old_value";

    /**
     * newValue对应表字段
     */
    public static final String COLUMN_NEW_VALUE = "new_value";

    /**
     * guidData逻辑名
     */
    public static final String NAME_GUID_DATA = "操作数据GUID";

    /**
     * physicalName逻辑名
     */
    public static final String NAME_PHYSICAL_NAME = "变化项字段名";

    /**
     * logicName逻辑名
     */
    public static final String NAME_LOGIC_NAME = "变化项逻辑名称";

    /**
     * oldValue逻辑名
     */
    public static final String NAME_OLD_VALUE = "变化之前值";

    /**
     * newValue逻辑名
     */
    public static final String NAME_NEW_VALUE = "变化之后值";

    /**
     * 操作数据GUID:全局唯一标识符（GUID，Globally Unique Identifier）
     */
    private String guidData;

    /**
     * 变化项字段名
     */
    private String physicalName;

    /**
     * 变化项逻辑名称
     */
    private String logicName;

    /**
     * 变化之前值
     */
    private String oldValue;

    /**
     * 变化之后值
     */
    private String newValue;

}

