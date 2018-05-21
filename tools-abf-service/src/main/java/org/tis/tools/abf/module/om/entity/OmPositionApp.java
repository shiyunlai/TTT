package org.tis.tools.abf.module.om.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * omPositionApp岗位所拥有（允许操作）的应用列表信息
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("om_position_app")
public class OmPositionApp implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "岗位应用列表";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidPosition对应表字段
     */
    public static final String COLUMN_GUID_POSITION = "guid_position";

    /**
     * guidApp对应表字段
     */
    public static final String COLUMN_GUID_APP = "guid_app";

    /**
     * guid逻辑名
     */
    public static final String NAME_GUID = "数据主键";

    /**
     * guidPosition逻辑名
     */
    public static final String NAME_GUID_POSITION = "岗位GUID";

    /**
     * guidApp逻辑名
     */
    public static final String NAME_GUID_APP = "应用GUID";

    /**
     * 数据主键:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    @TableId
    private String guid;

    /**
     * 岗位GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidPosition;

    /**
     * 应用GUID
     */
    private String guidApp;

}

