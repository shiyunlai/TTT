package org.tis.tools.model.auth;

import lombok.Data;

import java.util.Date;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/7/26
 **/
@Data
public class TisApp {

    private String guid;

    /**
     * 应用代码
     */
    private String appCode;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用类型:取值来自业务菜单： DICT_AC_APPTYPE
     * 如：本地，远程
     */
    public String appType;

    /**
     * 是否开通:取值来自业务菜单： DICT_YON
     * 默认为N，新建后，必须执行应用开通操作，才被开通。
     */
    public String isopen;

    /**
     * 开通时间:记录到时分秒
     */
    private Date openDate;

    /**
     * 访问地址:http://IP:PORT/service-name
     */
    private String url;

    /**
     * IP
     */
    private String ipAddr;

    /**
     * 端口
     */
    private String ipPort;

    /**
     * 应用描述
     */
    private String appDesc;
}
