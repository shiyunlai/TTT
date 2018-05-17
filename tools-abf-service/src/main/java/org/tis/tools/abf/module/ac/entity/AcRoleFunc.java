package org.tis.tools.abf.module.ac.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * acRoleFunc角色所包含的功能清单
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("ac_role_func")
public class AcRoleFunc implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "权限集(角色)功能对应关系";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidRole对应表字段
     */
    public static final String COLUMN_GUID_ROLE = "guid_role";

    /**
     * guidFunc对应表字段
     */
    public static final String COLUMN_GUID_FUNC = "guid_func";

    /**
     * guidApp对应表字段
     */
    public static final String COLUMN_GUID_APP = "guid_app";

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
     * guidFunc逻辑名
     */
    public static final String NAME_GUID_FUNC = "拥有功能GUID";

    /**
     * guidApp逻辑名
     */
    public static final String NAME_GUID_APP = "应用GUID";

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
     * 拥有功能GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidFunc;

    /**
     * 应用GUID:冗余字段
     */
    private String guidApp;

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

