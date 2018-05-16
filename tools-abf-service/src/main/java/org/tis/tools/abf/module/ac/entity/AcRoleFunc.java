package org.tis.tools.abf.module.ac.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * acRoleFunc角色所包含的功能清单
 * 
 * @author Auto Generate Tools
 * @date 2018/05/16
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

}

