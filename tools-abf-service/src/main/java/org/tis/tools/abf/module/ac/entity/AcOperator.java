package org.tis.tools.abf.module.ac.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * acOperator系统登录用户表，一个用户只能有一个或零个操作员
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("ac_operator")
public class AcOperator implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "操作员";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * userId对应表字段
     */
    public static final String COLUMN_USER_ID = "user_id";

    /**
     * password对应表字段
     */
    public static final String COLUMN_PASSWORD = "password";

    /**
     * operatorName对应表字段
     */
    public static final String COLUMN_OPERATOR_NAME = "operator_name";

    /**
     * operatorStatus对应表字段
     */
    public static final String COLUMN_OPERATOR_STATUS = "operator_status";

    /**
     * invalDate对应表字段
     */
    public static final String COLUMN_INVAL_DATE = "inval_date";

    /**
     * authMode对应表字段
     */
    public static final String COLUMN_AUTH_MODE = "auth_mode";

    /**
     * lockLimit对应表字段
     */
    public static final String COLUMN_LOCK_LIMIT = "lock_limit";

    /**
     * errCount对应表字段
     */
    public static final String COLUMN_ERR_COUNT = "err_count";

    /**
     * lockTime对应表字段
     */
    public static final String COLUMN_LOCK_TIME = "lock_time";

    /**
     * unlockTime对应表字段
     */
    public static final String COLUMN_UNLOCK_TIME = "unlock_time";

    /**
     * lastLogin对应表字段
     */
    public static final String COLUMN_LAST_LOGIN = "last_login";

    /**
     * startDate对应表字段
     */
    public static final String COLUMN_START_DATE = "start_date";

    /**
     * endDate对应表字段
     */
    public static final String COLUMN_END_DATE = "end_date";

    /**
     * validTime对应表字段
     */
    public static final String COLUMN_VALID_TIME = "valid_time";

    /**
     * macCode对应表字段
     */
    public static final String COLUMN_MAC_CODE = "mac_code";

    /**
     * ipAddress对应表字段
     */
    public static final String COLUMN_IP_ADDRESS = "ip_address";

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
     * userId逻辑名
     */
    public static final String NAME_USER_ID = "登录用户名";

    /**
     * password逻辑名
     */
    public static final String NAME_PASSWORD = "密码";

    /**
     * operatorName逻辑名
     */
    public static final String NAME_OPERATOR_NAME = "操作员姓名";

    /**
     * operatorStatus逻辑名
     */
    public static final String NAME_OPERATOR_STATUS = "操作员状态";

    /**
     * invalDate逻辑名
     */
    public static final String NAME_INVAL_DATE = "密码失效日期";

    /**
     * authMode逻辑名
     */
    public static final String NAME_AUTH_MODE = "认证模式";

    /**
     * lockLimit逻辑名
     */
    public static final String NAME_LOCK_LIMIT = "锁定次数限制";

    /**
     * errCount逻辑名
     */
    public static final String NAME_ERR_COUNT = "当前错误登录次数";

    /**
     * lockTime逻辑名
     */
    public static final String NAME_LOCK_TIME = "锁定时间";

    /**
     * unlockTime逻辑名
     */
    public static final String NAME_UNLOCK_TIME = "解锁时间";

    /**
     * lastLogin逻辑名
     */
    public static final String NAME_LAST_LOGIN = "最近登录时间";

    /**
     * startDate逻辑名
     */
    public static final String NAME_START_DATE = "有效开始日期";

    /**
     * endDate逻辑名
     */
    public static final String NAME_END_DATE = "有效截止日期";

    /**
     * validTime逻辑名
     */
    public static final String NAME_VALID_TIME = "允许时间范围";

    /**
     * macCode逻辑名
     */
    public static final String NAME_MAC_CODE = "允许MAC码";

    /**
     * ipAddress逻辑名
     */
    public static final String NAME_IP_ADDRESS = "允许IP地址";

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
     * 登录用户名
     */
    private String userId;

    /**
     * 密码
     */
    private String password;

    /**
     * 操作员姓名:记录当前操作员姓名（只记录当前值，不随之改变）
     */
    private String operatorName;

    /**
     * 操作员状态:取值来自业务菜单：DICT_AC_OPERATOR_STATUS
     * 正常，挂起，注销，锁定...
     * 系统处理状态间的流转
     */
    private String operatorStatus;

    /**
     * 密码失效日期:指定失效时间具体到时分秒
     */
    private Date invalDate;

    /**
     * 认证模式:取值来自业务菜单：DICT_AC_AUTHMODE
     * 如：本地密码认证、LDAP认证、等
     * 可以多选，以逗号分隔，且按照出现先后顺序进行认证；
     * 如：
     * pwd,captcha
     * 表示输入密码，并且还需要验证码
     */
    private String authMode;

    /**
     * 锁定次数限制:登陆错误超过本数字，系统锁定操作员，默认5次。
     * 可为操作员单独设置；
     */
    private BigDecimal lockLimit;

    /**
     * 当前错误登录次数
     */
    private BigDecimal errCount;

    /**
     * 锁定时间
     */
    private Date lockTime;

    /**
     * 解锁时间:当状态为锁定时，解锁的时间
     */
    private Date unlockTime;

    /**
     * 最近登录时间
     */
    private Date lastLogin;

    /**
     * 有效开始日期:启用操作员时设置，任何时间可设置；
     */
    private Date startDate;

    /**
     * 有效截止日期:启用操作员时设置，任何时间可设置；
     */
    private Date endDate;

    /**
     * 允许时间范围:定义一个规则表达式，表示允许操作的有效时间范围，格式为：
     * [{begin:"HH:mm",end:"HH:mm"},{begin:"HH:mm",end:"HH:mm"},...]
     * 如：
     * [{begin:"08:00",end:"11:30"},{begin:"14:30",end:"17:00"}]
     * 表示，该操作员被允许每天有两个时间段进行系统操作，分别 早上08:00 - 11:30，下午14:30 － 17:00
     */
    private String validTime;

    /**
     * 允许MAC码:允许设置多个MAC，以逗号分隔，控制操作员只能在这些机器上登陆。
     */
    private String macCode;

    /**
     * 允许IP地址:允许设置多个IP地址
     */
    private String ipAddress;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 最近更新时间
     */
    private Date lastupdate;

    /**
     * 最近更新人员
     */
    private String updator;

    /**
     * 数据状态:0 有效
     * D 删除（逻辑删除）
     */
    private String dataStatus;

}

