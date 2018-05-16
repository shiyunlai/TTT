package org.tis.tools.abf.module.ac.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * acRoleDatascope配置角色具有的数据权限。
 * 说明角色拥有某个实体数据中哪些范围的操作权。
 * 
 * @author Auto Generate Tools
 * @date 2018/05/16
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

}

