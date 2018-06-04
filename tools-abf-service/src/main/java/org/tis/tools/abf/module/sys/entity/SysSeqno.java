package org.tis.tools.abf.module.sys.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;
import org.tis.tools.abf.module.sys.entity.enums.SeqnoReset;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * sysSeqno每个SEQ_KEY表示一个序号资源，顺序增加使用序号。
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("sys_seqno")
public class SysSeqno implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "序号资源表";

    /**
     * seqName对应表字段
     */
    public static final String COLUMN_SEQ_NAME = "seq_name";

    /**
     * seqKey对应表字段
     */
    public static final String COLUMN_SEQ_KEY = "seq_key";

    /**
     * seqNo对应表字段
     */
    public static final String COLUMN_SEQ_NO = "seq_no";

    /**
     * reset对应表字段
     */
    public static final String COLUMN_RESET = "reset";

    /**
     * resetParams对应表字段
     */
    public static final String COLUMN_RESET_PARAMS = "reset_params";

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
     * seqName逻辑名
     */
    public static final String NAME_SEQ_NAME = "序号资源表名称";

    /**
     * seqKey逻辑名
     */
    public static final String NAME_SEQ_KEY = "序号键值";

    /**
     * seqNo逻辑名
     */
    public static final String NAME_SEQ_NO = "序号数";

    /**
     * reset逻辑名
     */
    public static final String NAME_RESET = "重置方式";

    /**
     * resetParams逻辑名
     */
    public static final String NAME_RESET_PARAMS = "重置处理参数";

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
     * 序号资源表名称:序号资源的名称，如:柜员660001的交易流水序号资源
     */
    private String seqName;

    /**
     * 序号键值
     */
    @TableId
    private String seqKey;

    /**
     * 序号数:顺序增加的数字
     */
    private BigDecimal seqNo;

    /**
     * 重置方式:来自业务字典： DICT_SYS_RESET
     * 如：
     * 不重置（默认）
     * 按天重置
     * 按周重置
     * 自定义重置周期（按指定时间间隔重置）
     * ...
     */
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private SeqnoReset reset ;

    /**
     * 重置处理参数:重置程序执行时的输入参数，通过本参数指定六重置周期，重置执行时间，重置起始数字等
     */
    private String resetParams;

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

