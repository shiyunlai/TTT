package org.tis.tools.abf.module.om.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * omEmpPosition定义人员和岗位的对应关系，需要注明，一个人员可以设定一个基本岗位
 * 
 * @author Auto Generate Tools
 * @date 2018/05/16
 */
@Data
@TableName("om_emp_position")
public class OmEmpPosition implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "员工岗位对应关系";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidEmp对应表字段
     */
    public static final String COLUMN_GUID_EMP = "guid_emp";

    /**
     * guidPosition对应表字段
     */
    public static final String COLUMN_GUID_POSITION = "guid_position";

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
     * guidPosition逻辑名
     */
    public static final String NAME_GUID_POSITION = "所在岗位GUID";

    /**
     * ismain逻辑名
     */
    public static final String NAME_ISMAIN = "是否主岗位";

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
     * 所在岗位GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidPosition;

    /**
     * 是否主岗位:取值来自业务菜单：DICT_YON
     * 只能有一个主岗位
     */
    private String ismain;

}

