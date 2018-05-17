package org.tis.tools.abf.module.ac.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * acRoleEntityfield角色与实体字段（属性）的对应关系。
 * 说明某个角色拥有哪些属性的操作权。
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("ac_role_entityfield")
public class AcRoleEntityfield implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "角色与实体属性关系";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidRole对应表字段
     */
    public static final String COLUMN_GUID_ROLE = "guid_role";

    /**
     * guidEntityfield对应表字段
     */
    public static final String COLUMN_GUID_ENTITYFIELD = "guid_entityfield";

    /**
     * ismodify对应表字段
     */
    public static final String COLUMN_ISMODIFY = "ismodify";

    /**
     * isview对应表字段
     */
    public static final String COLUMN_ISVIEW = "isview";

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
     * guidEntityfield逻辑名
     */
    public static final String NAME_GUID_ENTITYFIELD = "拥有实体属性GUID";

    /**
     * ismodify逻辑名
     */
    public static final String NAME_ISMODIFY = "可修改";

    /**
     * isview逻辑名
     */
    public static final String NAME_ISVIEW = "可查看";

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
     * 拥有实体属性GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidEntityfield;

    /**
     * 可修改:取值来自业务菜单： DICT_YON
     */
    private String ismodify;

    /**
     * 可查看:取值来自业务菜单： DICT_YON
     */
    private String isview;

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

