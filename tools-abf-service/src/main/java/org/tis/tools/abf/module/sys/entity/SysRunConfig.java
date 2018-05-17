package org.tis.tools.abf.module.sys.entity;

import com.baomidou.mybatisplus.annotations.*;

import java.util.Date;

import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.io.Serializable;

/**
 * sysRunConfig运行期系统参数表，以三段式结构进行参数存储
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("sys_run_config")
public class SysRunConfig implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "系统运行参数表";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidApp对应表字段
     */
    public static final String COLUMN_GUID_APP = "guid_app";

    /**
     * groupName对应表字段
     */
    public static final String COLUMN_GROUP_NAME = "group_name";

    /**
     * keyName对应表字段
     */
    public static final String COLUMN_KEY_NAME = "key_name";

    /**
     * valueFrom对应表字段
     */
    public static final String COLUMN_VALUE_FROM = "value_from";

    /**
     * value对应表字段
     */
    public static final String COLUMN_VALUE = "value";

    /**
     * description对应表字段
     */
    public static final String COLUMN_DESCRIPTION = "description";

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
     * guidApp逻辑名
     */
    public static final String NAME_GUID_APP = "应用系统GUID";

    /**
     * groupName逻辑名
     */
    public static final String NAME_GROUP_NAME = "参数组别";

    /**
     * keyName逻辑名
     */
    public static final String NAME_KEY_NAME = "参数键";

    /**
     * valueFrom逻辑名
     */
    public static final String NAME_VALUE_FROM = "值来源类型";

    /**
     * value逻辑名
     */
    public static final String NAME_VALUE = "参数值";

    /**
     * description逻辑名
     */
    public static final String NAME_DESCRIPTION = "参数描述";

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
     * 应用系统GUID:用于表识一组参数属于某个应用系统 。下拉AC_APP表记录
     */
    private String guidApp;

    /**
     * 参数组别:参数组别，手工输入
     */
    private String groupName;

    /**
     * 参数键:参数键名称，手工输入
     */
    private String keyName;

    /**
     * 值来源类型:H：手工指定
     * 或者选择业务字典的GUID（此时存储业务字典名称 SYS_DICT.DICT_KEY)
     */
    private String valueFrom;

    /**
     * 参数值:当value_from为H时，手工输入任意有效字符串；
     * 当value_from为业务字典时，下拉选择；
     */
    private String value;

    /**
     * 参数描述:参数功能描述
     */
    private String description;

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
    @TableField(fill = FieldFill.UPDATE)
    private String updator;

    /**
     * 数据状态:0 有效
     * D 删除（逻辑删除）
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private String dataStatus;

}

