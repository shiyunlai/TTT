package org.tis.tools.abf.module.ac.entity;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.*;

import java.util.Date;

import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.io.Serializable;

/**
 * acOperatorMenu操作员对自己在某个应用系统的菜单重组
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("ac_operator_menu")
public class AcOperatorMenu implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "操作员重组菜单";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidOperator对应表字段
     */
    public static final String COLUMN_GUID_OPERATOR = "guid_operator";

    /**
     * guidApp对应表字段
     */
    public static final String COLUMN_GUID_APP = "guid_app";

    /**
     * guidFunc对应表字段
     */
    public static final String COLUMN_GUID_FUNC = "guid_func";

    /**
     * menuName对应表字段
     */
    public static final String COLUMN_MENU_NAME = "menu_name";

    /**
     * menuLabel对应表字段
     */
    public static final String COLUMN_MENU_LABEL = "menu_label";

    /**
     * guidParents对应表字段
     */
    public static final String COLUMN_GUID_PARENTS = "guid_parents";

    /**
     * isleaf对应表字段
     */
    public static final String COLUMN_ISLEAF = "isleaf";

    /**
     * uiEntry对应表字段
     */
    public static final String COLUMN_UI_ENTRY = "ui_entry";

    /**
     * menuLevel对应表字段
     */
    public static final String COLUMN_MENU_LEVEL = "menu_level";

    /**
     * guidRoot对应表字段
     */
    public static final String COLUMN_GUID_ROOT = "guid_root";

    /**
     * displayOrder对应表字段
     */
    public static final String COLUMN_DISPLAY_ORDER = "display_order";

    /**
     * imagePath对应表字段
     */
    public static final String COLUMN_IMAGE_PATH = "image_path";

    /**
     * expandPath对应表字段
     */
    public static final String COLUMN_EXPAND_PATH = "expand_path";

    /**
     * menuSeq对应表字段
     */
    public static final String COLUMN_MENU_SEQ = "menu_seq";

    /**
     * openMode对应表字段
     */
    public static final String COLUMN_OPEN_MODE = "open_mode";

    /**
     * subCount对应表字段
     */
    public static final String COLUMN_SUB_COUNT = "sub_count";

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
     * guidOperator逻辑名
     */
    public static final String NAME_GUID_OPERATOR = "操作员GUID";

    /**
     * guidApp逻辑名
     */
    public static final String NAME_GUID_APP = "适用于应用";

    /**
     * guidFunc逻辑名
     */
    public static final String NAME_GUID_FUNC = "功能GUID";

    /**
     * menuName逻辑名
     */
    public static final String NAME_MENU_NAME = "菜单名称";

    /**
     * menuLabel逻辑名
     */
    public static final String NAME_MENU_LABEL = "菜单显示（中文）";

    /**
     * guidParents逻辑名
     */
    public static final String NAME_GUID_PARENTS = "父菜单GUID";

    /**
     * isleaf逻辑名
     */
    public static final String NAME_ISLEAF = "是否叶子菜单";

    /**
     * uiEntry逻辑名
     */
    public static final String NAME_UI_ENTRY = "UI入口";

    /**
     * menuLevel逻辑名
     */
    public static final String NAME_MENU_LEVEL = "菜单层次";

    /**
     * guidRoot逻辑名
     */
    public static final String NAME_GUID_ROOT = "根菜单GUID";

    /**
     * displayOrder逻辑名
     */
    public static final String NAME_DISPLAY_ORDER = "显示顺序";

    /**
     * imagePath逻辑名
     */
    public static final String NAME_IMAGE_PATH = "菜单图片路径";

    /**
     * expandPath逻辑名
     */
    public static final String NAME_EXPAND_PATH = "菜单展开图片路径";

    /**
     * menuSeq逻辑名
     */
    public static final String NAME_MENU_SEQ = "菜单路径序列";

    /**
     * openMode逻辑名
     */
    public static final String NAME_OPEN_MODE = "页面打开方式";

    /**
     * subCount逻辑名
     */
    public static final String NAME_SUB_COUNT = "子节点数";

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
     * 操作员GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidOperator;

    /**
     * 适用于应用
     */
    private String guidApp;

    /**
     * 功能GUID
     */
    private String guidFunc;

    /**
     * 菜单名称:显示为菜单的名称
     */
    private String menuName;

    /**
     * 菜单显示（中文）
     */
    private String menuLabel;

    /**
     * 父菜单GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidParents;

    /**
     * 是否叶子菜单:见业务菜单： DICT_YON
     */
    private String isleaf;

    /**
     * UI入口:针对EXT模式提供，例如abf_auth/function/module.xml
     */
    private String uiEntry;

    /**
     * 菜单层次:原类型smallint
     */
    private BigDecimal menuLevel;

    /**
     * 根菜单GUID
     */
    private String guidRoot;

    /**
     * 显示顺序:原类型smallint
     */
    private BigDecimal displayOrder;

    /**
     * 菜单图片路径
     */
    private String imagePath;

    /**
     * 菜单展开图片路径
     */
    private String expandPath;

    /**
     * 菜单路径序列
     */
    private String menuSeq;

    /**
     * 页面打开方式:数值取自业务菜单： DICT_AC_OPENMODE
     * 如：主窗口打开、弹出窗口打开...
     */
    private String openMode;

    /**
     * 子节点数
     */
    private BigDecimal subCount;

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

