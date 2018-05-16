package org.tis.tools.abf.module.jnl.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import org.tis.tools.abf.module.jnl.entity.enums.DataOperateType;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;

import java.io.Serializable;
import java.util.List;

/**
 * logAbfData记录客户与网点系统接触的所有日志明细，这些接触行为包括：
 * 客户主动接触网点，如：使用自助设备；
 * 柜员主动接触客户，如：厅堂经理介绍业务，高柜柜员为客户办理交易等；
 * 客户间接接触网点，如：社区银行营销活动，客户通过商家积分兑换了银行理财优惠券；
 * 
 * @author Auto Generate Tools
 * @date 2018/05/16
 */
@Data
@TableName("log_abf_data")
public class LogAbfData implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "操作的数据记录";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidOperate对应表字段
     */
    public static final String COLUMN_GUID_OPERATE = "guid_operate";

    /**
     * operateType对应表字段
     */
    public static final String COLUMN_OPERATE_TYPE = "operate_type";

    /**
     * dataGuid对应表字段
     */
    public static final String COLUMN_DATA_GUID = "data_guid";

    /**
     * dataName对应表字段
     */
    public static final String COLUMN_DATA_NAME = "data_name";

    /**
     * dataClass对应表字段
     */
    public static final String COLUMN_DATA_CLASS = "data_class";

    /**
     * dataString对应表字段
     */
    public static final String COLUMN_DATA_STRING = "data_string";

    /**
     * guid逻辑名
     */
    public static final String NAME_GUID = "数据主键";

    /**
     * guidOperate逻辑名
     */
    public static final String NAME_GUID_OPERATE = "操作日志GUID";

    /**
     * operateType逻辑名
     */
    public static final String NAME_OPERATE_TYPE = "数据操作类型";

    /**
     * dataGuid逻辑名
     */
    public static final String NAME_DATA_GUID = "数据GUID";

    /**
     * dataName逻辑名
     */
    public static final String NAME_DATA_NAME = "数据名称";

    /**
     * dataClass逻辑名
     */
    public static final String NAME_DATA_CLASS = "数据类名";

    /**
     * dataString逻辑名
     */
    public static final String NAME_DATA_STRING = "数据STRING值";

    /**
     * 数据主键:全局唯一标识符（GUID，Globally Unique Identifier）
     */
    @TableId
    private String guid;

    /**
     * 操作日志GUID:全局唯一标识符（GUID，Globally Unique Identifier）
     */
    private String guidOperate;

    /**
     * 数据操作类型
     */
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    public DataOperateType operateType;

    /**
     * 数据GUID
     */
    private String dataGuid;

    /**
     * 数据名称
     */
    private String dataName;

    /**
     * 数据类名
     */
    private String dataClass;

    /**
     * 数据STRING值
     */
    private String dataString;


}

