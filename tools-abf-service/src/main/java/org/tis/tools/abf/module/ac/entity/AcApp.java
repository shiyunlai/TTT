package org.tis.tools.abf.module.ac.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import org.tis.tools.abf.module.ac.entity.enums.AcAppType;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;

import java.io.Serializable;

/**
 * acApp应用系统（Application）注册表
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("ac_app")
public class AcApp implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "应用系统";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * appCode对应表字段
     */
    public static final String COLUMN_APP_CODE = "app_code";

    /**
     * appName对应表字段
     */
    public static final String COLUMN_APP_NAME = "app_name";

    /**
     * appType对应表字段
     */
    public static final String COLUMN_APP_TYPE = "app_type";

    /**
     * isopen对应表字段
     */
    public static final String COLUMN_ISOPEN = "isopen";

    /**
     * openDate对应表字段
     */
    public static final String COLUMN_OPEN_DATE = "open_date";

    /**
     * url对应表字段
     */
    public static final String COLUMN_URL = "url";

    /**
     * ipAddr对应表字段
     */
    public static final String COLUMN_IP_ADDR = "ip_addr";

    /**
     * ipPort对应表字段
     */
    public static final String COLUMN_IP_PORT = "ip_port";

    /**
     * appDesc对应表字段
     */
    public static final String COLUMN_APP_DESC = "app_desc";

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
     * appCode逻辑名
     */
    public static final String NAME_APP_CODE = "应用代码";

    /**
     * appName逻辑名
     */
    public static final String NAME_APP_NAME = "应用名称";

    /**
     * appType逻辑名
     */
    public static final String NAME_APP_TYPE = "应用类型";

    /**
     * isopen逻辑名
     */
    public static final String NAME_ISOPEN = "是否开通";

    /**
     * openDate逻辑名
     */
    public static final String NAME_OPEN_DATE = "开通时间";

    /**
     * url逻辑名
     */
    public static final String NAME_URL = "访问地址";

    /**
     * ipAddr逻辑名
     */
    public static final String NAME_IP_ADDR = "IP";

    /**
     * ipPort逻辑名
     */
    public static final String NAME_IP_PORT = "端口";

    /**
     * appDesc逻辑名
     */
    public static final String NAME_APP_DESC = "应用描述";

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
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    public AcAppType appType;

    /**
     * 是否开通:取值来自业务菜单： DICT_YON
     * 默认为N，新建后，必须执行应用开通操作，才被开通。
     */
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    public YON isopen;

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

