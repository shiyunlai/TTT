package org.tis.tools.abf.module.sys.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.*;

import java.util.Date;

import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.io.Serializable;

/**
 * sysDutyDef职务及responsiblity。定义职务及上下级关系（可以把“职务”理解为岗位的岗位类型，岗位是在机构、部门中实例化后的职务，如：A机构设有‘总经理’这个岗位，其职务为‘总经理’）
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("sys_duty_def")
public class SysDutyDef implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "职务定义表";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * dutyCode对应表字段
     */
    public static final String COLUMN_DUTY_CODE = "duty_code";

    /**
     * dutyName对应表字段
     */
    public static final String COLUMN_DUTY_NAME = "duty_name";

    /**
     * dutyType对应表字段
     */
    public static final String COLUMN_DUTY_TYPE = "duty_type";

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
     * dutyLevel对应表字段
     */
    public static final String COLUMN_DUTY_LEVEL = "duty_level";

    /**
     * dutySeq对应表字段
     */
    public static final String COLUMN_DUTY_SEQ = "duty_seq";

    /**
     * remark对应表字段
     */
    public static final String COLUMN_REMARK = "remark";

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
     * dutyCode逻辑名
     */
    public static final String NAME_DUTY_CODE = "职务代码";

    /**
     * dutyName逻辑名
     */
    public static final String NAME_DUTY_NAME = "职务名称";

    /**
     * dutyType逻辑名
     */
    public static final String NAME_DUTY_TYPE = "职务套别";

    /**
     * guidParents逻辑名
     */
    public static final String NAME_GUID_PARENTS = "父职务GUID";

    /**
     * isleaf逻辑名
     */
    public static final String NAME_ISLEAF = "是否叶子节点";

    /**
     * subCount逻辑名
     */
    public static final String NAME_SUB_COUNT = "子节点数";

    /**
     * dutyLevel逻辑名
     */
    public static final String NAME_DUTY_LEVEL = "职务层次";

    /**
     * dutySeq逻辑名
     */
    public static final String NAME_DUTY_SEQ = "职务序列";

    /**
     * remark逻辑名
     */
    public static final String NAME_REMARK = "备注";

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
     * 职务代码
     */
    private String dutyCode;

    /**
     * 职务名称
     */
    private String dutyName;

    /**
     * 职务套别:见业务字典： DICT_OM_DUTYTYPE
     * 例如科技类，审计类等
     * 实际记录了 字典项的GUID （SYS_DITC_ITEM）
     */
    private String dutyType;

    /**
     * 父职务GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidParents;

    /**
     * 是否叶子节点:取值来自业务菜单：DICT_YON
     */
    private String isleaf;

    /**
     * 子节点数
     */
    private BigDecimal subCount;

    /**
     * 职务层次
     */
    private BigDecimal dutyLevel;

    /**
     * 职务序列:职务的面包屑定位信息
     */
    private String dutySeq;

    /**
     * 备注
     */
    private String remark;

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

