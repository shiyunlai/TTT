package org.tis.tools.abf.module.om.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.*;

import java.util.Date;

import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.io.Serializable;

/**
 * omGroup工作组定义表，用于定义临时组、虚拟组，跨部门的项目组等。
 * 工作组实质上与机构类似，是为了将项目组、工作组等临时性的组织机构管理起来，业务上通常工作组有一定的时效性，是一个非常设机构。
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("om_group")
public class OmGroup implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "工作组";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * groupCode对应表字段
     */
    public static final String COLUMN_GROUP_CODE = "group_code";

    /**
     * groupName对应表字段
     */
    public static final String COLUMN_GROUP_NAME = "group_name";

    /**
     * groupType对应表字段
     */
    public static final String COLUMN_GROUP_TYPE = "group_type";

    /**
     * groupStatus对应表字段
     */
    public static final String COLUMN_GROUP_STATUS = "group_status";

    /**
     * groupDesc对应表字段
     */
    public static final String COLUMN_GROUP_DESC = "group_desc";

    /**
     * guidEmpManager对应表字段
     */
    public static final String COLUMN_GUID_EMP_MANAGER = "guid_emp_manager";

    /**
     * guidOrg对应表字段
     */
    public static final String COLUMN_GUID_ORG = "guid_org";

    /**
     * guidParents对应表字段
     */
    public static final String COLUMN_GUID_PARENTS = "guid_parents";

    /**
     * isleaf对应表字段
     */
    public static final String COLUMN_ISLEAF = "isleaf";

    /**
     * subCount对应表字段
     */
    public static final String COLUMN_SUB_COUNT = "sub_count";

    /**
     * groupLevel对应表字段
     */
    public static final String COLUMN_GROUP_LEVEL = "group_level";

    /**
     * groupSeq对应表字段
     */
    public static final String COLUMN_GROUP_SEQ = "group_seq";

    /**
     * startDate对应表字段
     */
    public static final String COLUMN_START_DATE = "start_date";

    /**
     * endDate对应表字段
     */
    public static final String COLUMN_END_DATE = "end_date";

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
     * groupCode逻辑名
     */
    public static final String NAME_GROUP_CODE = "工作组代码";

    /**
     * groupName逻辑名
     */
    public static final String NAME_GROUP_NAME = "工作组名称";

    /**
     * groupType逻辑名
     */
    public static final String NAME_GROUP_TYPE = "工作组类型";

    /**
     * groupStatus逻辑名
     */
    public static final String NAME_GROUP_STATUS = "工作组状态";

    /**
     * groupDesc逻辑名
     */
    public static final String NAME_GROUP_DESC = "工作组描述";

    /**
     * guidEmpManager逻辑名
     */
    public static final String NAME_GUID_EMP_MANAGER = "负责人";

    /**
     * guidOrg逻辑名
     */
    public static final String NAME_GUID_ORG = "隶属机构GUID";

    /**
     * guidParents逻辑名
     */
    public static final String NAME_GUID_PARENTS = "父工作组GUID";

    /**
     * isleaf逻辑名
     */
    public static final String NAME_ISLEAF = "是否叶子节点";

    /**
     * subCount逻辑名
     */
    public static final String NAME_SUB_COUNT = "子节点数";

    /**
     * groupLevel逻辑名
     */
    public static final String NAME_GROUP_LEVEL = "工作组层次";

    /**
     * groupSeq逻辑名
     */
    public static final String NAME_GROUP_SEQ = "工作组序列";

    /**
     * startDate逻辑名
     */
    public static final String NAME_START_DATE = "工作组有效开始日期";

    /**
     * endDate逻辑名
     */
    public static final String NAME_END_DATE = "工作组有效截止日期";

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
     * 工作组代码:业务上对工作组的编码
     */
    private String groupCode;

    /**
     * 工作组名称
     */
    private String groupName;

    /**
     * 工作组类型:见业务字典： DICT_OM_GROUPTYPE
     */
    private String groupType;

    /**
     * 工作组状态:见业务字典： DICT_OM_GROUPSTATUS
     */
    private String groupStatus;

    /**
     * 工作组描述
     */
    private String groupDesc;

    /**
     * 负责人:选择范围来自 OM_EMPLOYEE表
     */
    private String guidEmpManager;

    /**
     * 隶属机构GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidOrg;

    /**
     * 父工作组GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidParents;

    /**
     * 是否叶子节点:见业务菜单： DICT_YON
     */
    private String isleaf;

    /**
     * 子节点数
     */
    private BigDecimal subCount;

    /**
     * 工作组层次
     */
    private BigDecimal groupLevel;

    /**
     * 工作组序列:本工作组的面包屑定位信息
     */
    private String groupSeq;

    /**
     * 工作组有效开始日期
     */
    private Date startDate;

    /**
     * 工作组有效截止日期
     */
    private Date endDate;

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

