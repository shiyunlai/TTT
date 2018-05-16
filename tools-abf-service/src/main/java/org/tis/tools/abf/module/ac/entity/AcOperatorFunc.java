package org.tis.tools.abf.module.ac.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * acOperatorFunc针对人员配置的特殊权限，如特别开通的功能，或者特别禁止的功能
 * 
 * @author Auto Generate Tools
 * @date 2018/05/16
 */
@Data
@TableName("ac_operator_func")
public class AcOperatorFunc implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "操作员特殊权限配置";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidOperator对应表字段
     */
    public static final String COLUMN_GUID_OPERATOR = "guid_operator";

    /**
     * guidFunc对应表字段
     */
    public static final String COLUMN_GUID_FUNC = "guid_func";

    /**
     * authType对应表字段
     */
    public static final String COLUMN_AUTH_TYPE = "auth_type";

    /**
     * startDate对应表字段
     */
    public static final String COLUMN_START_DATE = "start_date";

    /**
     * endDate对应表字段
     */
    public static final String COLUMN_END_DATE = "end_date";

    /**
     * guidApp对应表字段
     */
    public static final String COLUMN_GUID_APP = "guid_app";

    /**
     * guid逻辑名
     */
    public static final String NAME_GUID = "数据主键";

    /**
     * guidOperator逻辑名
     */
    public static final String NAME_GUID_OPERATOR = "操作员GUID";

    /**
     * guidFunc逻辑名
     */
    public static final String NAME_GUID_FUNC = "功能GUID";

    /**
     * authType逻辑名
     */
    public static final String NAME_AUTH_TYPE = "授权标志";

    /**
     * startDate逻辑名
     */
    public static final String NAME_START_DATE = "有效开始日期";

    /**
     * endDate逻辑名
     */
    public static final String NAME_END_DATE = "有效截至日期";

    /**
     * guidApp逻辑名
     */
    public static final String NAME_GUID_APP = "应用GUID";

    /**
     * 数据主键:全局唯一标识符（GUID，Globally Unique Identifier）
     */
    @TableId
    private String guid;

    /**
     * 操作员GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidOperator;

    /**
     * 功能GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidFunc;

    /**
     * 授权标志:取值来自业务菜单：DICT_AC_AUTHTYPE
     * 如：特别禁止、特别允许
     */
    private String authType;

    /**
     * 有效开始日期
     */
    private Date startDate;

    /**
     * 有效截至日期
     */
    private Date endDate;

    /**
     * 应用GUID:冗余字段
     */
    private String guidApp;

}

