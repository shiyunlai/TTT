package org.tis.tools.abf.module.ac.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.*;

import java.util.Date;

import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;
import org.tis.tools.abf.module.ac.entity.enums.FuncType;
import org.tis.tools.abf.module.common.entity.enums.YON;

import java.io.Serializable;

/**
 * acFunc功能&行为，菜单
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("ac_func")
public class AcFunc implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "功能行为";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidApp对应表字段
     */
    public static final String COLUMN_GUID_APP = "guid_app";

    /**
     * funcType对应表字段
     */
    public static final String COLUMN_FUNC_TYPE = "func_type";

    /**
     * funcCode对应表字段
     */
    public static final String COLUMN_FUNC_CODE = "func_code";

    /**
     * funcName对应表字段
     */
    public static final String COLUMN_FUNC_NAME = "func_name";

    /**
     * funcDesc对应表字段
     */
    public static final String COLUMN_FUNC_DESC = "func_desc";

    /**
     * isopen对应表字段
     */
    public static final String COLUMN_ISOPEN = "isopen";

    /**
     * ischeck对应表字段
     */
    public static final String COLUMN_ISCHECK = "ischeck";

    /**
     * guidFunc对应表字段
     */
    public static final String COLUMN_GUID_FUNC = "guid_func";

    /**
     * displayOrder对应表字段
     */
    public static final String COLUMN_DISPLAY_ORDER = "display_order";

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
     * guidApp逻辑名
     */
    public static final String NAME_GUID_APP = "隶属应用";

    /**
     * funcType逻辑名
     */
    public static final String NAME_FUNC_TYPE = "功能类型";

    /**
     * funcCode逻辑名
     */
    public static final String NAME_FUNC_CODE = "功能编号";

    /**
     * funcName逻辑名
     */
    public static final String NAME_FUNC_NAME = "功能名称";

    /**
     * funcDesc逻辑名
     */
    public static final String NAME_FUNC_DESC = "功能描述";

    /**
     * isopen逻辑名
     */
    public static final String NAME_ISOPEN = "是否启用";

    /**
     * ischeck逻辑名
     */
    public static final String NAME_ISCHECK = "是否验证权限";

    /**
     * guidFunc逻辑名
     */
    public static final String NAME_GUID_FUNC = "父";

    /**
     * displayOrder逻辑名
     */
    public static final String NAME_DISPLAY_ORDER = "显示顺序";

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
     * 隶属应用:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidApp;

    /**
     * 功能类型:取值来自业务菜单：DICT_AC_FUNCTYPE
     * F：功能（Function）
     * B：行为（Behave）
     */
    private FuncType funcType;

    /**
     * 功能编号:业务上对功能的编码
     */
    private String funcCode;

    /**
     * 功能名称
     */
    private String funcName;

    /**
     * 功能描述
     */
    private String funcDesc;

    /**
     * 是否启用:Y 启用(默认）
     * N 停用（不出现在菜单中）
     */
    private YON isopen;

    /**
     * 是否验证权限:取值来自业务菜单： DICT_YON
     * N：无需验权（只要有看见菜单，所有人都能执行本功能）
     * Y：需进行权限验证（默认）
     */
    private YON ischeck;

    /**
     * 父:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidFunc;

    /**
     * 显示顺序:所在层次内的展示顺序
     */
    private BigDecimal displayOrder;

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
    @TableField(fill = FieldFill.UPDATE)
    private String updator;

    /**
     * 数据状态:0 有效
     * D 删除（逻辑删除）
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private String dataStatus;

}

