package org.tis.tools.abf.module.jnl.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import org.tis.tools.abf.module.jnl.entity.enums.OperateResult;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;

import java.io.Serializable;
import java.util.List;

/**
 * logAbfOperate记录操作员对ABF系统的操作日志（交易操作日志另见： LOG_TX_TRACE）
 * 
 * @author Auto Generate Tools
 * @date 2018/05/16
 */
@Data
@TableName("log_abf_operate")
public class LogAbfOperate implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "操作日志";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * operateFrom对应表字段
     */
    public static final String COLUMN_OPERATE_FROM = "operate_from";

    /**
     * operateType对应表字段
     */
    public static final String COLUMN_OPERATE_TYPE = "operate_type";

    /**
     * operateTime对应表字段
     */
    public static final String COLUMN_OPERATE_TIME = "operate_time";

    /**
     * operateResult对应表字段
     */
    public static final String COLUMN_OPERATE_RESULT = "operate_result";

    /**
     * operateDesc对应表字段
     */
    public static final String COLUMN_OPERATE_DESC = "operate_desc";

    /**
     * operatorName对应表字段
     */
    public static final String COLUMN_OPERATOR_NAME = "operator_name";

    /**
     * userId对应表字段
     */
    public static final String COLUMN_USER_ID = "user_id";

    /**
     * appCode对应表字段
     */
    public static final String COLUMN_APP_CODE = "app_code";

    /**
     * appName对应表字段
     */
    public static final String COLUMN_APP_NAME = "app_name";

    /**
     * serviceId对应表字段
     */
    public static final String COLUMN_SERVICE_ID = "service_id";

    /**
     * restfulUrl对应表字段
     */
    public static final String COLUMN_RESTFUL_URL = "restful_url";

    /**
     * stackTrace对应表字段
     */
    public static final String COLUMN_STACK_TRACE = "stack_trace";

    /**
     * processDesc对应表字段
     */
    public static final String COLUMN_PROCESS_DESC = "process_desc";

    /**
     * guid逻辑名
     */
    public static final String NAME_GUID = "数据主键";

    /**
     * operateFrom逻辑名
     */
    public static final String NAME_OPERATE_FROM = "操作渠道";

    /**
     * operateType逻辑名
     */
    public static final String NAME_OPERATE_TYPE = "操作类型";

    /**
     * operateTime逻辑名
     */
    public static final String NAME_OPERATE_TIME = "操作时间";

    /**
     * operateResult逻辑名
     */
    public static final String NAME_OPERATE_RESULT = "操作结果";

    /**
     * operateDesc逻辑名
     */
    public static final String NAME_OPERATE_DESC = "操作描述";

    /**
     * operatorName逻辑名
     */
    public static final String NAME_OPERATOR_NAME = "操作员姓名";

    /**
     * userId逻辑名
     */
    public static final String NAME_USER_ID = "操作员";

    /**
     * appCode逻辑名
     */
    public static final String NAME_APP_CODE = "应用代码";

    /**
     * appName逻辑名
     */
    public static final String NAME_APP_NAME = "应用名称";

    /**
     * serviceId逻辑名
     */
    public static final String NAME_SERVICE_ID = "服务ID";

    /**
     * restfulUrl逻辑名
     */
    public static final String NAME_RESTFUL_URL = "服务地址";

    /**
     * stackTrace逻辑名
     */
    public static final String NAME_STACK_TRACE = "异常堆栈";

    /**
     * processDesc逻辑名
     */
    public static final String NAME_PROCESS_DESC = "处理描述";

    /**
     * 数据主键:全局唯一标识符（GUID，Globally Unique Identifier）
     */
    @TableId
    private String guid;

    /**
     * 操作渠道
     */
    private String operateFrom;

    /**
     * 操作类型:见业务字典：DICT_OPERATOR_TYPE
     */
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    public OperateType operateType;

    /**
     * 操作时间
     */
    private Date operateTime;

    /**
     * 操作结果:见业务字典：DICT_OPERATOR_RESULT
     */
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    public OperateResult operateResult;

    /**
     * 操作描述
     */
    private String operateDesc;

    /**
     * 操作员姓名:记录当前操作员姓名（只记录当前值，不随之改变）
     */
    private String operatorName;

    /**
     * 操作员:登陆用户id
     */
    private String userId;

    /**
     * 应用代码
     */
    private String appCode;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 服务ID
     */
    private String serviceId;

    /**
     * 服务地址:功能对应的RESTFul服务地址
     */
    private String restfulUrl;

    /**
     * 异常堆栈:记录异常堆栈信息，超过4000的部分被自动丢弃
     */
    private String stackTrace;

    /**
     * 处理描述:记录功能执行时的业务处理信息
     */
    private String processDesc;


}

