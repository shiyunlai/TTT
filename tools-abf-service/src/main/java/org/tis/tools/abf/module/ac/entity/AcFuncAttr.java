package org.tis.tools.abf.module.ac.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * acFuncAttr功能表字段之外的属性
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("ac_func_attr")
public class AcFuncAttr implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "功能属性";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidFunc对应表字段
     */
    public static final String COLUMN_GUID_FUNC = "guid_func";

    /**
     * attrType对应表字段
     */
    public static final String COLUMN_ATTR_TYPE = "attr_type";

    /**
     * attrKey对应表字段
     */
    public static final String COLUMN_ATTR_KEY = "attr_key";

    /**
     * attrValue对应表字段
     */
    public static final String COLUMN_ATTR_VALUE = "attr_value";

    /**
     * memo对应表字段
     */
    public static final String COLUMN_MEMO = "memo";

    /**
     * createtime对应表字段
     */
    public static final String COLUMN_CREATETIME = "createtime";

    /**
     * lastupdate对应表字段
     */
    public static final String COLUMN_LASTUPDATE = "lastupdate";

    /**
     * updator对应表字段
     */
    public static final String COLUMN_UPDATOR = "updator";

    /**
     * dataStatus对应表字段
     */
    public static final String COLUMN_DATA_STATUS = "data_status";

    /**
     * guid逻辑名
     */
    public static final String NAME_GUID = "数据主键";

    /**
     * guidFunc逻辑名
     */
    public static final String NAME_GUID_FUNC = "对应功能GUID";

    /**
     * attrType逻辑名
     */
    public static final String NAME_ATTR_TYPE = "属性类型";

    /**
     * attrKey逻辑名
     */
    public static final String NAME_ATTR_KEY = "属性名";

    /**
     * attrValue逻辑名
     */
    public static final String NAME_ATTR_VALUE = "属性名";

    /**
     * memo逻辑名
     */
    public static final String NAME_MEMO = "备注";

    /**
     * createtime逻辑名
     */
    public static final String NAME_CREATETIME = "创建时间";

    /**
     * lastupdate逻辑名
     */
    public static final String NAME_LASTUPDATE = "最近更新时间";

    /**
     * updator逻辑名
     */
    public static final String NAME_UPDATOR = "最近更新人员";

    /**
     * dataStatus逻辑名
     */
    public static final String NAME_DATA_STATUS = "数据状态";

    /**
     * 数据主键:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    @TableId
    private String guid;

    /**
     * 对应功能GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidFunc;

    /**
     * 属性类型
     */
    private String attrType;

    /**
     * 属性名
     */
    private String attrKey;

    /**
     * 属性名
     */
    private String attrValue;

    /**
     * 备注
     */
    private String memo;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 最近更新时间
     */
    private Date lastupdate;

    /**
     * 最近更新人员
     */
    private String updator;

    /**
     * 数据状态:0 有效
     * D 删除（逻辑删除）
     */
    private String dataStatus;

}

