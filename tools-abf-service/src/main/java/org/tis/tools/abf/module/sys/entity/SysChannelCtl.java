package org.tis.tools.abf.module.sys.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * sysChannelCtl
 * 
 * @author Auto Generate Tools
 * @date 2018/05/16
 */
@Data
@TableName("sys_channel_ctl")
public class SysChannelCtl implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "渠道参数控制表";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * chnCode对应表字段
     */
    public static final String COLUMN_CHN_CODE = "chn_code";

    /**
     * chnName对应表字段
     */
    public static final String COLUMN_CHN_NAME = "chn_name";

    /**
     * chnRemark对应表字段
     */
    public static final String COLUMN_CHN_REMARK = "chn_remark";

    /**
     * guid逻辑名
     */
    public static final String NAME_GUID = "数据主键";

    /**
     * chnCode逻辑名
     */
    public static final String NAME_CHN_CODE = "渠道代码";

    /**
     * chnName逻辑名
     */
    public static final String NAME_CHN_NAME = "渠道名称";

    /**
     * chnRemark逻辑名
     */
    public static final String NAME_CHN_REMARK = "渠道备注信息";

    /**
     * 数据主键:全局唯一标识符（GUID，Globally Unique Identifier）
     */
    @TableId
    private String guid;

    /**
     * 渠道代码:记录接触系统对应的渠道代码
     */
    private String chnCode;

    /**
     * 渠道名称
     */
    private String chnName;

    /**
     * 渠道备注信息
     */
    private String chnRemark;

}

