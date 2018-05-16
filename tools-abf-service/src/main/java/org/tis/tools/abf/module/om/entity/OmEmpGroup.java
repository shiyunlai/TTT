package org.tis.tools.abf.module.om.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * omEmpGroup定义工作组包含的人员（工作组中有哪些人员）
 * 如：某个项目组有哪些人员
 * 
 * @author Auto Generate Tools
 * @date 2018/05/16
 */
@Data
@TableName("om_emp_group")
public class OmEmpGroup implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "人员工作组对应关系";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidEmp对应表字段
     */
    public static final String COLUMN_GUID_EMP = "guid_emp";

    /**
     * guidGroup对应表字段
     */
    public static final String COLUMN_GUID_GROUP = "guid_group";

    /**
     * guid逻辑名
     */
    public static final String NAME_GUID = "数据主键";

    /**
     * guidEmp逻辑名
     */
    public static final String NAME_GUID_EMP = "员工GUID";

    /**
     * guidGroup逻辑名
     */
    public static final String NAME_GUID_GROUP = "隶属工作组GUID";

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
     * 隶属工作组GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidGroup;

}

