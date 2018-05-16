package org.tis.tools.abf.module.ac.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * acRoleEntity角色与数据实体的对应关系。
 * 说明角色拥有哪些实体操作权。
 * 
 * @author Auto Generate Tools
 * @date 2018/05/16
 */
@Data
@TableName("ac_role_entity")
public class AcRoleEntity implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "角色实体关系";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidRole对应表字段
     */
    public static final String COLUMN_GUID_ROLE = "guid_role";

    /**
     * guidEntity对应表字段
     */
    public static final String COLUMN_GUID_ENTITY = "guid_entity";

    /**
     * isadd对应表字段
     */
    public static final String COLUMN_ISADD = "isadd";

    /**
     * isdel对应表字段
     */
    public static final String COLUMN_ISDEL = "isdel";

    /**
     * ismodify对应表字段
     */
    public static final String COLUMN_ISMODIFY = "ismodify";

    /**
     * isview对应表字段
     */
    public static final String COLUMN_ISVIEW = "isview";

    /**
     * guid逻辑名
     */
    public static final String NAME_GUID = "数据主键";

    /**
     * guidRole逻辑名
     */
    public static final String NAME_GUID_ROLE = "角色GUID";

    /**
     * guidEntity逻辑名
     */
    public static final String NAME_GUID_ENTITY = "拥有实体GUID";

    /**
     * isadd逻辑名
     */
    public static final String NAME_ISADD = "可增加";

    /**
     * isdel逻辑名
     */
    public static final String NAME_ISDEL = "可删除";

    /**
     * ismodify逻辑名
     */
    public static final String NAME_ISMODIFY = "可修改";

    /**
     * isview逻辑名
     */
    public static final String NAME_ISVIEW = "可查看";

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
     * 拥有实体GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidEntity;

    /**
     * 可增加:取值来自业务菜单： DICT_YON
     */
    private String isadd;

    /**
     * 可删除:取值来自业务菜单： DICT_YON
     */
    private String isdel;

    /**
     * 可修改:取值来自业务菜单： DICT_YON
     */
    private String ismodify;

    /**
     * 可查看:取值来自业务菜单： DICT_YON
     */
    private String isview;

}

