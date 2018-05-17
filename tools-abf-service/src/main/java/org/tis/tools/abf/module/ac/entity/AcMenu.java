package org.tis.tools.abf.module.ac.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.*;

import java.util.Date;

import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.io.Serializable;

/**
 * acMenu应用菜单表，从逻辑上为某个应用系统中的功能组织为一个有分类，有层级的树结构。
 * UI可根据菜单数据结构，进行界面呈现（PC上，PAD上，手机上....充分考虑用户交互体验）
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("ac_menu")
public class AcMenu implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "菜单";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

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
     * menuCode对应表字段
     */
    public static final String COLUMN_MENU_CODE = "menu_code";

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
     * guidParents对应表字段
     */
    public static final String COLUMN_GUID_PARENTS = "guid_parents";

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
    public static final String NAME_GUID_APP = "应用GUID";

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
     * menuCode逻辑名
     */
    public static final String NAME_MENU_CODE = "菜单代码";

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
     * guidParents逻辑名
     */
    public static final String NAME_GUID_PARENTS = "父菜单GUID";

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
    public static final String NAME_IMAGE_PATH = "菜单闭合图片路径";

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
     * 应用GUID
     */
    private String guidApp;

    /**
     * 功能GUID
     */
    private String guidFunc;

    /**
     * 菜单名称:菜单树上显示的名称，一般同功能名称
     */
    private String menuName;

    /**
     * 菜单显示（中文）
     */
    private String menuLabel;

    /**
     * 菜单代码:业务上对本菜单记录的编码
     */
    private String menuCode;

    /**
     * 是否叶子菜单:数值取自业务菜单：DICT_YON
     */
    private String isleaf;

    /**
     * UI入口:针对EXT模式提供，例如abf_auth/function/module.xml
     */
    private String uiEntry;

    /**
     * 菜单层次:原类型smalint
     */
    private BigDecimal menuLevel;

    /**
     * 父菜单GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidParents;

    /**
     * 根菜单GUID:本菜单所在菜单树的根节点菜单GUID
     */
    private String guidRoot;

    /**
     * 显示顺序:原类型smalint
     */
    private BigDecimal displayOrder;

    /**
     * 菜单闭合图片路径
     */
    private String imagePath;

    /**
     * 菜单展开图片路径
     */
    private String expandPath;

    /**
     * 菜单路径序列:类似面包屑导航，可以看出菜单的全路径；
     * 从应用系统开始，系统自动维护，如： /teller/loan/TX010112
     * 表示柜面系统（teller）中贷款功能组（loan）中的TX010112功能（交易）
     */
    private String menuSeq;

    /**
     * 页面打开方式:数值取自业务菜单： DICT_AC_OPENMODE
     * 如：主窗口打开、弹出窗口打开...
     */
    private String openMode;

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

