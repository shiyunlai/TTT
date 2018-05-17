package org.tis.tools.abf.module.ac.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.*;

import java.util.Date;

import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.io.Serializable;

/**
 * acOperatorShortcut针对应用系统定义快捷菜单
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("ac_operator_shortcut")
public class AcOperatorShortcut implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "操作员快捷菜单";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidOperator对应表字段
     */
    public static final String COLUMN_GUID_OPERATOR = "guid_operator";

    /**
     * shortcutKey对应表字段
     */
    public static final String COLUMN_SHORTCUT_KEY = "shortcut_key";

    /**
     * guidApp对应表字段
     */
    public static final String COLUMN_GUID_APP = "guid_app";

    /**
     * guidFunc对应表字段
     */
    public static final String COLUMN_GUID_FUNC = "guid_func";

    /**
     * aliasFuncName对应表字段
     */
    public static final String COLUMN_ALIAS_FUNC_NAME = "alias_func_name";

    /**
     * orderNo对应表字段
     */
    public static final String COLUMN_ORDER_NO = "order_no";

    /**
     * imagePath对应表字段
     */
    public static final String COLUMN_IMAGE_PATH = "image_path";

    /**
     * expandPath对应表字段
     */
    public static final String COLUMN_EXPAND_PATH = "expand_path";

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
     * shortcutKey逻辑名
     */
    public static final String NAME_SHORTCUT_KEY = "快捷按键";

    /**
     * guidApp逻辑名
     */
    public static final String NAME_GUID_APP = "应用GUID";

    /**
     * guidFunc逻辑名
     */
    public static final String NAME_GUID_FUNC = "功能GUID";

    /**
     * aliasFuncName逻辑名
     */
    public static final String NAME_ALIAS_FUNC_NAME = "功能别名";

    /**
     * orderNo逻辑名
     */
    public static final String NAME_ORDER_NO = "排列顺序";

    /**
     * imagePath逻辑名
     */
    public static final String NAME_IMAGE_PATH = "快捷菜单图片路径";

    /**
     * expandPath逻辑名
     */
    public static final String NAME_EXPAND_PATH = "菜单展开图片路径";

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
     * 快捷按键:如：CTRL+1 表示启动TX010505，本字段记录 CTRL+1 这个信息
     */
    private String shortcutKey;

    /**
     * 应用GUID:冗余字段，方便为快捷键分组
     */
    private String guidApp;

    /**
     * 功能GUID
     */
    private String guidFunc;

    /**
     * 功能别名
     */
    private String aliasFuncName;

    /**
     * 排列顺序:原类型smallint
     */
    private BigDecimal orderNo;

    /**
     * 快捷菜单图片路径:操作员自定义时指定，不指定则保持
     */
    private String imagePath;

    /**
     * 菜单展开图片路径:自定时指定，不指定则保持
     */
    private String expandPath;

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

