package org.tis.tools.model.auth;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/7/26
 **/
@Data
public class TisOperator {

    /**
     * ID
     */
    private String guid;

    /**
     * userId
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

}
