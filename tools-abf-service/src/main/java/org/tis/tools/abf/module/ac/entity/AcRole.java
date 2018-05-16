package org.tis.tools.abf.module.ac.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.ac.controller.request.AcRoleUpdateValidateGrop;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;

import java.io.Serializable;

/**
 * acRole权限集（角色）定义表
 * 
 * @author Auto Generate Tools
 * @date 2018/05/16
 */
@Data
@TableName("ac_role")
public class AcRole implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "权限集(角色)";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * roleCode对应表字段
     */
    public static final String COLUMN_ROLE_CODE = "role_code";

    /**
     * roleName对应表字段
     */
    public static final String COLUMN_ROLE_NAME = "role_name";

    /**
     * enabled对应表字段
     */
    public static final String COLUMN_ENABLED = "enabled";

    /**
     * roleDesc对应表字段
     */
    public static final String COLUMN_ROLE_DESC = "role_desc";

    /**
     * guid逻辑名
     */
    public static final String NAME_GUID = "数据主键";

    /**
     * roleCode逻辑名
     */
    public static final String NAME_ROLE_CODE = "角色代码";

    /**
     * roleName逻辑名
     */
    public static final String NAME_ROLE_NAME = "角色名称";

    /**
     * enabled逻辑名
     */
    public static final String NAME_ENABLED = "是否启用";

    /**
     * roleDesc逻辑名
     */
    public static final String NAME_ROLE_DESC = "角色描述";

    /**
     * 数据主键:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    @TableId
    private String guid;

    /**
     * 角色代码:业务上对角色的编码
     */
    @NotBlank(message = "roleCode不能为空", groups = {AcRoleUpdateValidateGrop.class})
    public String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 是否启用
     */
    @JSONField(deserializeUsing= CommonEnumDeserializer.class)
    public YON enabled;

    /**
     * 角色描述
     */
    private String roleDesc;

}

