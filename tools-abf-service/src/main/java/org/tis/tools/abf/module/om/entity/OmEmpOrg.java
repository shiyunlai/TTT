package org.tis.tools.abf.module.om.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * omEmpOrg定义人员和机构的关系表（机构有哪些人员）。
 * 允许一个人员同时在多个机构，但是只能有一个主机构。
 * 
 * @author Auto Generate Tools
 * @date 2018/05/16
 */
@Data
@TableName("om_emp_org")
public class OmEmpOrg implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "员工隶属机构关系表";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidEmp对应表字段
     */
    public static final String COLUMN_GUID_EMP = "guid_emp";

    /**
     * guidOrg对应表字段
     */
    public static final String COLUMN_GUID_ORG = "guid_org";

    /**
     * ismain对应表字段
     */
    public static final String COLUMN_ISMAIN = "ismain";

    /**
     * guid逻辑名
     */
    public static final String NAME_GUID = "数据主键";

    /**
     * guidEmp逻辑名
     */
    public static final String NAME_GUID_EMP = "员工GUID";

    /**
     * guidOrg逻辑名
     */
    public static final String NAME_GUID_ORG = "隶属机构GUID";

    /**
     * ismain逻辑名
     */
    public static final String NAME_ISMAIN = "是否主机构";

    /**
     * 数据主键:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    @TableId
    private String guid;

    /**
     * 员工GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidEmp;

    /**
     * 隶属机构GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidOrg;

    /**
     * 是否主机构:取值来自业务菜单： DICT_YON
     * 必须有且只能有一个主机构，默认Y，人员管理时程序检查当前是否只有一条主机构；
     */
    private String ismain;

}

