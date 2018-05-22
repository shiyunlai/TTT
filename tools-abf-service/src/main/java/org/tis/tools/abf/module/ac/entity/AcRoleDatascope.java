package org.tis.tools.abf.module.ac.entity;

import com.baomidou.mybatisplus.annotations.*;

import java.util.Date;

import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.io.Serializable;

/**
 * acRoleDatascope配置角色具有的数据权限。
 * 说明角色拥有某个实体数据中哪些范围的操作权。
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("ac_role_datascope")
public class AcRoleDatascope implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "角色数据范围权限对应";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidRole对应表字段
     */
    public static final String COLUMN_GUID_ROLE = "guid_role";

    /**
     * guidDatascope对应表字段
     */
    public static final String COLUMN_GUID_DATASCOPE = "guid_datascope";

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
     * guidRole逻辑名
     */
    public static final String NAME_GUID_ROLE = "角色GUID";

    /**
     * guidDatascope逻辑名
     */
    public static final String NAME_GUID_DATASCOPE = "拥有数据范围GUID";

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
     * 数据主键:全局唯一标识符（GUID，Globally Unique Identifier）
     */
    @TableId
    private String guid;

    /**
     * 角色GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidRole;

    /**
     * 拥有数据范围GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidDatascope;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createtime;

    /**
     * 最近更新时间
     */
    @Version
    @TableField(fill = FieldFill.UPDATE)
    private Date lastupdate;

    /**
     * 最近更新人员
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updator;

    /**
     * 数据状态:0 有效
     * D 删除（逻辑删除）
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private String dataStatus;

}

