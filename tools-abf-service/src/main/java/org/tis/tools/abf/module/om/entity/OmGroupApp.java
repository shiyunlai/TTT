package org.tis.tools.abf.module.om.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * omGroupApp工作组所拥有（允许操作）的应用列表
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("om_group_app")
public class OmGroupApp implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "工作组应用列表";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidGroup对应表字段
     */
    public static final String COLUMN_GUID_GROUP = "guid_group";

    /**
     * guidApp对应表字段
     */
    public static final String COLUMN_GUID_APP = "guid_app";

    /**
     * guid逻辑名
     */
    public static final String NAME_GUID = "数据主键";

    /**
     * guidGroup逻辑名
     */
    public static final String NAME_GUID_GROUP = "工作组GUID";

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
     * 工作组GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidGroup;

    /**
     * 应用GUID
     */
    private String guidApp;

}

