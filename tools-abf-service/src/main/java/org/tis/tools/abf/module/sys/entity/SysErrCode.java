package org.tis.tools.abf.module.sys.entity;

import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;
import org.tis.tools.abf.module.sys.entity.enums.ErrCodeKind;

import java.io.Serializable;
import java.util.Date;

/**
 * sysErrCode记录系统中的各种错误代码信息，如系统抛出的错误信息，交易执行时的错误码等
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("sys_err_code")
public class SysErrCode implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "错误码表";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * errcodeKind对应表字段
     */
    public static final String COLUMN_ERRCODE_KIND = "errcode_kind";

    /**
     * errCode对应表字段
     */
    public static final String COLUMN_ERR_CODE = "err_code";

    /**
     * errMsg对应表字段
     */
    public static final String COLUMN_ERR_MSG = "err_msg";

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
     * errcodeKind逻辑名
     */
    public static final String NAME_ERRCODE_KIND = "错误代码分类";

    /**
     * errCode逻辑名
     */
    public static final String NAME_ERR_CODE = "错误代码";

    /**
     * errMsg逻辑名
     */
    public static final String NAME_ERR_MSG = "错误信息";

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
     * 数据主键:全局唯一标识符（GUID，Globally Unique Identifier）
     */
    @TableId
    private String guid;

    /**
     * 错误代码分类:见业务字典： DICT_ERRCODE_KIND
     * SYS 系统错误码
     * TRANS 交易错误码
     */
    private ErrCodeKind errcodeKind;

    /**
     * 错误代码
     */
    private String errCode;

    /**
     * 错误信息
     */
    private String errMsg;

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

