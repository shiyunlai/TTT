package org.tis.tools.abf.module.ac.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * acEntityfield数据实体的字段（属性）定义表
 * 
 * @author Auto Generate Tools
 * @date 2018/05/16
 */
@Data
@TableName("ac_entityfield")
public class AcEntityfield implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "实体属性";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidEntity对应表字段
     */
    public static final String COLUMN_GUID_ENTITY = "guid_entity";

    /**
     * fieldName对应表字段
     */
    public static final String COLUMN_FIELD_NAME = "field_name";

    /**
     * fieldDesc对应表字段
     */
    public static final String COLUMN_FIELD_DESC = "field_desc";

    /**
     * displayFormat对应表字段
     */
    public static final String COLUMN_DISPLAY_FORMAT = "display_format";

    /**
     * doclistCode对应表字段
     */
    public static final String COLUMN_DOCLIST_CODE = "doclist_code";

    /**
     * checkboxValue对应表字段
     */
    public static final String COLUMN_CHECKBOX_VALUE = "checkbox_value";

    /**
     * fkInputurl对应表字段
     */
    public static final String COLUMN_FK_INPUTURL = "fk_inputurl";

    /**
     * fkFielddesc对应表字段
     */
    public static final String COLUMN_FK_FIELDDESC = "fk_fielddesc";

    /**
     * fkColumnname对应表字段
     */
    public static final String COLUMN_FK_COLUMNNAME = "fk_columnname";

    /**
     * fkTablename对应表字段
     */
    public static final String COLUMN_FK_TABLENAME = "fk_tablename";

    /**
     * descFieldname对应表字段
     */
    public static final String COLUMN_DESC_FIELDNAME = "desc_fieldname";

    /**
     * refType对应表字段
     */
    public static final String COLUMN_REF_TYPE = "ref_type";

    /**
     * fieldType对应表字段
     */
    public static final String COLUMN_FIELD_TYPE = "field_type";

    /**
     * displayOrder对应表字段
     */
    public static final String COLUMN_DISPLAY_ORDER = "display_order";

    /**
     * columnName对应表字段
     */
    public static final String COLUMN_COLUMN_NAME = "column_name";

    /**
     * width对应表字段
     */
    public static final String COLUMN_WIDTH = "width";

    /**
     * defaultValue对应表字段
     */
    public static final String COLUMN_DEFAULT_VALUE = "default_value";

    /**
     * minValue对应表字段
     */
    public static final String COLUMN_MIN_VALUE = "min_value";

    /**
     * maxValue对应表字段
     */
    public static final String COLUMN_MAX_VALUE = "max_value";

    /**
     * lengthValue对应表字段
     */
    public static final String COLUMN_LENGTH_VALUE = "length_value";

    /**
     * precisionValue对应表字段
     */
    public static final String COLUMN_PRECISION_VALUE = "precision_value";

    /**
     * validateType对应表字段
     */
    public static final String COLUMN_VALIDATE_TYPE = "validate_type";

    /**
     * ismodify对应表字段
     */
    public static final String COLUMN_ISMODIFY = "ismodify";

    /**
     * isdisplay对应表字段
     */
    public static final String COLUMN_ISDISPLAY = "isdisplay";

    /**
     * isinput对应表字段
     */
    public static final String COLUMN_ISINPUT = "isinput";

    /**
     * ispk对应表字段
     */
    public static final String COLUMN_ISPK = "ispk";

    /**
     * isautokey对应表字段
     */
    public static final String COLUMN_ISAUTOKEY = "isautokey";

    /**
     * guid逻辑名
     */
    public static final String NAME_GUID = "数据主键";

    /**
     * guidEntity逻辑名
     */
    public static final String NAME_GUID_ENTITY = "隶属实体GUID";

    /**
     * fieldName逻辑名
     */
    public static final String NAME_FIELD_NAME = "属性名称";

    /**
     * fieldDesc逻辑名
     */
    public static final String NAME_FIELD_DESC = "属性描述";

    /**
     * displayFormat逻辑名
     */
    public static final String NAME_DISPLAY_FORMAT = "显示格式";

    /**
     * doclistCode逻辑名
     */
    public static final String NAME_DOCLIST_CODE = "代码大类";

    /**
     * checkboxValue逻辑名
     */
    public static final String NAME_CHECKBOX_VALUE = "CHECKBOX_VALUE";

    /**
     * fkInputurl逻辑名
     */
    public static final String NAME_FK_INPUTURL = "外键录入URL";

    /**
     * fkFielddesc逻辑名
     */
    public static final String NAME_FK_FIELDDESC = "外键描述字段名";

    /**
     * fkColumnname逻辑名
     */
    public static final String NAME_FK_COLUMNNAME = "外键列名";

    /**
     * fkTablename逻辑名
     */
    public static final String NAME_FK_TABLENAME = "外键表名";

    /**
     * descFieldname逻辑名
     */
    public static final String NAME_DESC_FIELDNAME = "描述字段名";

    /**
     * refType逻辑名
     */
    public static final String NAME_REF_TYPE = "引用类型";

    /**
     * fieldType逻辑名
     */
    public static final String NAME_FIELD_TYPE = "字段类型";

    /**
     * displayOrder逻辑名
     */
    public static final String NAME_DISPLAY_ORDER = "顺序";

    /**
     * columnName逻辑名
     */
    public static final String NAME_COLUMN_NAME = "数据库列名";

    /**
     * width逻辑名
     */
    public static final String NAME_WIDTH = "宽度";

    /**
     * defaultValue逻辑名
     */
    public static final String NAME_DEFAULT_VALUE = "缺省值";

    /**
     * minValue逻辑名
     */
    public static final String NAME_MIN_VALUE = "最小值";

    /**
     * maxValue逻辑名
     */
    public static final String NAME_MAX_VALUE = "最大值";

    /**
     * lengthValue逻辑名
     */
    public static final String NAME_LENGTH_VALUE = "长度";

    /**
     * precisionValue逻辑名
     */
    public static final String NAME_PRECISION_VALUE = "小数位";

    /**
     * validateType逻辑名
     */
    public static final String NAME_VALIDATE_TYPE = "页面校验类型";

    /**
     * ismodify逻辑名
     */
    public static final String NAME_ISMODIFY = "是否可修改";

    /**
     * isdisplay逻辑名
     */
    public static final String NAME_ISDISPLAY = "是否显示";

    /**
     * isinput逻辑名
     */
    public static final String NAME_ISINPUT = "是否必须填写";

    /**
     * ispk逻辑名
     */
    public static final String NAME_ISPK = "是否是主键";

    /**
     * isautokey逻辑名
     */
    public static final String NAME_ISAUTOKEY = "是否自动产生主键";

    /**
     * 数据主键:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    @TableId
    private String guid;

    /**
     * 隶属实体GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidEntity;

    /**
     * 属性名称
     */
    private String fieldName;

    /**
     * 属性描述
     */
    private String fieldDesc;

    /**
     * 显示格式:如：属性为日期时，可以设置显示格式 yyyy/MM/dd；
     * 当查询出数据，返回给调用着之前生效本显示格式（返回的数据已经被格式化）；
     */
    private String displayFormat;

    /**
     * 代码大类
     */
    private String doclistCode;

    /**
     * CHECKBOX_VALUE
     */
    private String checkboxValue;

    /**
     * 外键录入URL
     */
    private String fkInputurl;

    /**
     * 外键描述字段名
     */
    private String fkFielddesc;

    /**
     * 外键列名
     */
    private String fkColumnname;

    /**
     * 外键表名
     */
    private String fkTablename;

    /**
     * 描述字段名
     */
    private String descFieldname;

    /**
     * 引用类型:0 业务字典
     * 1 其他表
     */
    private String refType;

    /**
     * 字段类型:0 字符串
     * 1 整数
     * 2 小数
     * 3 日期
     * 4 日期时间
     * 5 CHECKBOX
     * 6 引用
     */
    private String fieldType;

    /**
     * 顺序
     */
    private BigDecimal displayOrder;

    /**
     * 数据库列名
     */
    private String columnName;

    /**
     * 宽度
     */
    private BigDecimal width;

    /**
     * 缺省值
     */
    private String defaultValue;

    /**
     * 最小值
     */
    private String minValue;

    /**
     * 最大值
     */
    private String maxValue;

    /**
     * 长度
     */
    private BigDecimal lengthValue;

    /**
     * 小数位
     */
    private BigDecimal precisionValue;

    /**
     * 页面校验类型
     */
    private String validateType;

    /**
     * 是否可修改:取值来自业务菜单： DICT_YON
     */
    private String ismodify;

    /**
     * 是否显示:取值来自业务菜单： DICT_YON
     */
    private String isdisplay;

    /**
     * 是否必须填写:取值来自业务菜单： DICT_YON
     */
    private String isinput;

    /**
     * 是否是主键:取值来自业务菜单： DICT_YON
     */
    private String ispk;

    /**
     * 是否自动产生主键:取值来自业务菜单： DICT_YON
     */
    private String isautokey;

}

