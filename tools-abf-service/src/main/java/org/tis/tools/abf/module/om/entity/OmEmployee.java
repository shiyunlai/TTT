package org.tis.tools.abf.module.om.entity;

import com.baomidou.mybatisplus.annotations.*;

import java.util.Date;

import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.io.Serializable;

/**
 * omEmployee人员信息表
 * 人员至少隶属于一个机构；
 * 本表记录了：人员基本信息，人员联系信息，人员在机构中的信息，人员对应的操作员信息集成了人员的多个维度信息一起。
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("om_employee")
public class OmEmployee implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "员工";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * empCode对应表字段
     */
    public static final String COLUMN_EMP_CODE = "emp_code";

    /**
     * empName对应表字段
     */
    public static final String COLUMN_EMP_NAME = "emp_name";

    /**
     * empRealname对应表字段
     */
    public static final String COLUMN_EMP_REALNAME = "emp_realname";

    /**
     * gender对应表字段
     */
    public static final String COLUMN_GENDER = "gender";

    /**
     * empstatus对应表字段
     */
    public static final String COLUMN_EMPSTATUS = "empstatus";

    /**
     * guidOrg对应表字段
     */
    public static final String COLUMN_GUID_ORG = "guid_org";

    /**
     * guidPosition对应表字段
     */
    public static final String COLUMN_GUID_POSITION = "guid_position";

    /**
     * guidEmpMajor对应表字段
     */
    public static final String COLUMN_GUID_EMP_MAJOR = "guid_emp_major";

    /**
     * indate对应表字段
     */
    public static final String COLUMN_INDATE = "indate";

    /**
     * outdate对应表字段
     */
    public static final String COLUMN_OUTDATE = "outdate";

    /**
     * mobileno对应表字段
     */
    public static final String COLUMN_MOBILENO = "mobileno";

    /**
     * paperType对应表字段
     */
    public static final String COLUMN_PAPER_TYPE = "paper_type";

    /**
     * paperNo对应表字段
     */
    public static final String COLUMN_PAPER_NO = "paper_no";

    /**
     * birthdate对应表字段
     */
    public static final String COLUMN_BIRTHDATE = "birthdate";

    /**
     * htel对应表字段
     */
    public static final String COLUMN_HTEL = "htel";

    /**
     * haddress对应表字段
     */
    public static final String COLUMN_HADDRESS = "haddress";

    /**
     * hzipcode对应表字段
     */
    public static final String COLUMN_HZIPCODE = "hzipcode";

    /**
     * guidOperator对应表字段
     */
    public static final String COLUMN_GUID_OPERATOR = "guid_operator";

    /**
     * userId对应表字段
     */
    public static final String COLUMN_USER_ID = "user_id";

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
     * empCode逻辑名
     */
    public static final String NAME_EMP_CODE = "员工代码";

    /**
     * empName逻辑名
     */
    public static final String NAME_EMP_NAME = "员工姓名";

    /**
     * empRealname逻辑名
     */
    public static final String NAME_EMP_REALNAME = "员工全名";

    /**
     * gender逻辑名
     */
    public static final String NAME_GENDER = "性别";

    /**
     * empstatus逻辑名
     */
    public static final String NAME_EMPSTATUS = "员工状态";

    /**
     * guidOrg逻辑名
     */
    public static final String NAME_GUID_ORG = "主机构编号";

    /**
     * guidPosition逻辑名
     */
    public static final String NAME_GUID_POSITION = "基本岗位";

    /**
     * guidEmpMajor逻辑名
     */
    public static final String NAME_GUID_EMP_MAJOR = "直接主管";

    /**
     * indate逻辑名
     */
    public static final String NAME_INDATE = "入职日期";

    /**
     * outdate逻辑名
     */
    public static final String NAME_OUTDATE = "离职日期";

    /**
     * mobileno逻辑名
     */
    public static final String NAME_MOBILENO = "手机号码";

    /**
     * paperType逻辑名
     */
    public static final String NAME_PAPER_TYPE = "证件类型";

    /**
     * paperNo逻辑名
     */
    public static final String NAME_PAPER_NO = "证件号码";

    /**
     * birthdate逻辑名
     */
    public static final String NAME_BIRTHDATE = "出生日期";

    /**
     * htel逻辑名
     */
    public static final String NAME_HTEL = "家庭电话";

    /**
     * haddress逻辑名
     */
    public static final String NAME_HADDRESS = "家庭地址";

    /**
     * hzipcode逻辑名
     */
    public static final String NAME_HZIPCODE = "家庭邮编";

    /**
     * guidOperator逻辑名
     */
    public static final String NAME_GUID_OPERATOR = "操作员编号";

    /**
     * userId逻辑名
     */
    public static final String NAME_USER_ID = "操作员";

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
     * 员工代码
     */
    private String empCode;

    /**
     * 员工姓名
     */
    private String empName;

    /**
     * 员工全名
     */
    private String empRealname;

    /**
     * 性别:见业务菜单：DICT_OM_GENDER
     */
    private String gender;

    /**
     * 员工状态:见业务字典： DICT_OM_EMPSTATUS
     */
    private String empstatus;

    /**
     * 主机构编号:人员所属主机构编号（冗余设计）
     */
    private String guidOrg;

    /**
     * 基本岗位
     */
    private String guidPosition;

    /**
     * 直接主管
     */
    private String guidEmpMajor;

    /**
     * 入职日期
     */
    private Date indate;

    /**
     * 离职日期
     */
    private Date outdate;

    /**
     * 手机号码
     */
    private String mobileno;

    /**
     * 证件类型:见业务字典： DICT_SD_PAPERTYPE
     */
    private String paperType;

    /**
     * 证件号码
     */
    private String paperNo;

    /**
     * 出生日期
     */
    private Date birthdate;

    /**
     * 家庭电话
     */
    private String htel;

    /**
     * 家庭地址
     */
    private String haddress;

    /**
     * 家庭邮编:见业务字典： DICT_SD_ZIPCODE
     */
    private String hzipcode;

    /**
     * 操作员编号
     */
    private String guidOperator;

    /**
     * 操作员:登陆用户id
     */
    private String userId;

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

