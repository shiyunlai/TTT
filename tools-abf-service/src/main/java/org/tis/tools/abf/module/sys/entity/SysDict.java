package org.tis.tools.abf.module.sys.entity;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import org.tis.tools.abf.module.sys.entity.enums.DictFromType;
import org.tis.tools.abf.module.sys.entity.enums.DictType;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;

import java.io.Serializable;

/**
 * sysDict业务字典表，定义系统中下拉菜单的数据（注意：仅仅包括下拉菜单中的数据，而不包括下拉菜单样式，是否多选这些与下拉内容无关的信息）
 * 
 * @author Auto Generate Tools
 * @date 2018/05/16
 */
@Data
@TableName("sys_dict")
public class SysDict implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "业务字典";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * dictKey对应表字段
     */
    public static final String COLUMN_DICT_KEY = "dict_key";

    /**
     * dictType对应表字段
     */
    public static final String COLUMN_DICT_TYPE = "dict_type";

    /**
     * dictName对应表字段
     */
    public static final String COLUMN_DICT_NAME = "dict_name";

    /**
     * dictDesc对应表字段
     */
    public static final String COLUMN_DICT_DESC = "dict_desc";

    /**
     * guidParents对应表字段
     */
    public static final String COLUMN_GUID_PARENTS = "guid_parents";

    /**
     * defaultValue对应表字段
     */
    public static final String COLUMN_DEFAULT_VALUE = "default_value";

    /**
     * fromTable对应表字段
     */
    public static final String COLUMN_FROM_TABLE = "from_table";

    /**
     * useForKey对应表字段
     */
    public static final String COLUMN_USE_FOR_KEY = "use_for_key";

    /**
     * useForName对应表字段
     */
    public static final String COLUMN_USE_FOR_NAME = "use_for_name";

    /**
     * seqno对应表字段
     */
    public static final String COLUMN_SEQNO = "seqno";

    /**
     * sqlFilter对应表字段
     */
    public static final String COLUMN_SQL_FILTER = "sql_filter";

    /**
     * fromType对应表字段
     */
    public static final String COLUMN_FROM_TYPE = "from_type";

    /**
     * guid逻辑名
     */
    public static final String NAME_GUID = "数据主键";

    /**
     * dictKey逻辑名
     */
    public static final String NAME_DICT_KEY = "业务字典";

    /**
     * dictType逻辑名
     */
    public static final String NAME_DICT_TYPE = "类型";

    /**
     * dictName逻辑名
     */
    public static final String NAME_DICT_NAME = "字典名称";

    /**
     * dictDesc逻辑名
     */
    public static final String NAME_DICT_DESC = "解释说明";

    /**
     * guidParents逻辑名
     */
    public static final String NAME_GUID_PARENTS = "父字典GUID";

    /**
     * defaultValue逻辑名
     */
    public static final String NAME_DEFAULT_VALUE = "业务字典默认值";

    /**
     * fromTable逻辑名
     */
    public static final String NAME_FROM_TABLE = "字典项来源表";

    /**
     * useForKey逻辑名
     */
    public static final String NAME_USE_FOR_KEY = "作为字典项的列";

    /**
     * useForName逻辑名
     */
    public static final String NAME_USE_FOR_NAME = "作为字典项名称的列";

    /**
     * seqno逻辑名
     */
    public static final String NAME_SEQNO = "顺序号";

    /**
     * sqlFilter逻辑名
     */
    public static final String NAME_SQL_FILTER = "过滤条件";

    /**
     * fromType逻辑名
     */
    public static final String NAME_FROM_TYPE = "字典项来源类型";

    /**
     * 数据主键:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    @TableId
    private String guid;

    /**
     * 业务字典
     */
    private String dictKey;

    /**
     * 类型:见业务字典： DICT_TYPE
     * a 应用级（带业务含义的业务字典，应用开发时可扩展）
     * s 系统级（平台自己的业务字典）
     */
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    public DictType dictType;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 解释说明
     */
    private String dictDesc;

    /**
     * 父字典GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidParents;

    /**
     * 业务字典默认值:指定某个字典项（ITEM_VALUE）为本业务字典的默认值（用于扶助View层实现展示默认值）
     */
    private String defaultValue;

    /**
     * 字典项来源表:如果业务字典用来描述某个表中的字段选项，则本字段保存表名；
     * 其他情况默认为空；
     */
    private String fromTable;

    /**
     * 作为字典项的列:如果业务字典用来描述某个表中的字段选项，则本字段保存字段名；
     * 其他情况默认为空；
     */
    private String useForKey;

    /**
     * 作为字典项名称的列
     */
    private String useForName;

    /**
     * 顺序号:顺序号，从0开始排，按小到大排序
     */
    private BigDecimal seqno;

    /**
     * 过滤条件
     */
    private String sqlFilter;

    /**
     * 字典项来源类型:来源类型:0:来自字典项 1:来自单表  2:多表或视图
     */
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    public DictFromType fromType;

}

