package org.tis.tools.abf.module.om.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * omGroupPosition工作组岗位列表:一个工作组允许定义多个岗位，岗位之间允许存在层次关系
 * 
 * @author Auto Generate Tools
 * @date 2018/05/16
 */
@Data
@TableName("om_group_position")
public class OmGroupPosition implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "工作组岗位列表";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidGroup对应表字段
     */
    public static final String COLUMN_GUID_GROUP = "guid_group";

    /**
     * guidPosition对应表字段
     */
    public static final String COLUMN_GUID_POSITION = "guid_position";

    /**
     * guid逻辑名
     */
    public static final String NAME_GUID = "数据主键";

    /**
     * guidGroup逻辑名
     */
    public static final String NAME_GUID_GROUP = "工作组GUID";

    /**
     * guidPosition逻辑名
     */
    public static final String NAME_GUID_POSITION = "岗位GUID";

    /**
     * 数据主键:全局唯一标识符（GUID，Globally Unique Identifier）
     */
    @TableId
    private String guid;

    /**
     * 工作组GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidGroup;

    /**
     * 岗位GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidPosition;

}

