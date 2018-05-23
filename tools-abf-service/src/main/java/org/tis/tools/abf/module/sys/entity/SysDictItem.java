package org.tis.tools.abf.module.sys.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.*;

import java.util.Date;

import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.io.Serializable;

/**
 * sysDictItem业务字典内容项， 展示下拉菜单结构时，一般需要： 字典项，字典项名称，实际值
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("sys_dict_item")
public class SysDictItem implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "业务字典项";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidDict对应表字段
     */
    public static final String COLUMN_GUID_DICT = "guid_dict";

    /**
     * itemName对应表字段
     */
    public static final String COLUMN_ITEM_NAME = "item_name";

    /**
     * itemType对应表字段
     */
    public static final String COLUMN_ITEM_TYPE = "item_type";

    /**
     * itemValue对应表字段
     */
    public static final String COLUMN_ITEM_VALUE = "item_value";

    /**
     * sendValue对应表字段
     */
    public static final String COLUMN_SEND_VALUE = "send_value";

    /**
     * seqno对应表字段
     */
    public static final String COLUMN_SEQNO = "seqno";

    /**
     * itemDesc对应表字段
     */
    public static final String COLUMN_ITEM_DESC = "item_desc";

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
     * guidDict逻辑名
     */
    public static final String NAME_GUID_DICT = "隶属业务字典";

    /**
     * itemName逻辑名
     */
    public static final String NAME_ITEM_NAME = "字典项名称";

    /**
     * itemType逻辑名
     */
    public static final String NAME_ITEM_TYPE = "字典项类型";

    /**
     * itemValue逻辑名
     */
    public static final String NAME_ITEM_VALUE = "字典项";

    /**
     * sendValue逻辑名
     */
    public static final String NAME_SEND_VALUE = "实际值";

    /**
     * seqno逻辑名
     */
    public static final String NAME_SEQNO = "顺序号";

    /**
     * itemDesc逻辑名
     */
    public static final String NAME_ITEM_DESC = "字典项说明";

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
     * 隶属业务字典:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidDict;

    /**
     * 字典项名称
     */
    private String itemName;

    /**
     * 字典项类型:来自 dict 字典、value 实际值
     */
    private String itemType;

    /**
     * 字典项
     */
    private String itemValue;

    /**
     * 实际值:实际值，及选中字典项后，实际发送值给系统的数值。
     */
    private String sendValue;

    /**
     * 顺序号:顺序号，从0开始排，按小到大排序
     */
    private BigDecimal seqno;

    /**
     * 字典项说明
     */
    private String itemDesc;

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

