package org.tis.tools.abf.module.om.entity;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.om.entity.enums.OmOrgStatus;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;

import java.io.Serializable;

/**
 * omOrg机构部门（Organization）表
 * 允许定义多个平行机构
 * 
 * @author Auto Generate Tools
 * @date 2018/05/16
 */
@Data
@TableName("om_org")
public class OmOrg implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "机构信息表";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * orgCode对应表字段
     */
    public static final String COLUMN_ORG_CODE = "org_code";

    /**
     * orgName对应表字段
     */
    public static final String COLUMN_ORG_NAME = "org_name";

    /**
     * orgType对应表字段
     */
    public static final String COLUMN_ORG_TYPE = "org_type";

    /**
     * orgDegree对应表字段
     */
    public static final String COLUMN_ORG_DEGREE = "org_degree";

    /**
     * orgStatus对应表字段
     */
    public static final String COLUMN_ORG_STATUS = "org_status";

    /**
     * guidParents对应表字段
     */
    public static final String COLUMN_GUID_PARENTS = "guid_parents";

    /**
     * orgSeq对应表字段
     */
    public static final String COLUMN_ORG_SEQ = "org_seq";

    /**
     * orgAddr对应表字段
     */
    public static final String COLUMN_ORG_ADDR = "org_addr";

    /**
     * linkMan对应表字段
     */
    public static final String COLUMN_LINK_MAN = "link_man";

    /**
     * linkTel对应表字段
     */
    public static final String COLUMN_LINK_TEL = "link_tel";

    /**
     * startDate对应表字段
     */
    public static final String COLUMN_START_DATE = "start_date";

    /**
     * endDate对应表字段
     */
    public static final String COLUMN_END_DATE = "end_date";

    /**
     * area对应表字段
     */
    public static final String COLUMN_AREA = "area";

    /**
     * sortNo对应表字段
     */
    public static final String COLUMN_SORT_NO = "sort_no";

    /**
     * isleaf对应表字段
     */
    public static final String COLUMN_ISLEAF = "isleaf";

    /**
     * remark对应表字段
     */
    public static final String COLUMN_REMARK = "remark";

    /**
     * guid逻辑名
     */
    public static final String NAME_GUID = "数据主键";

    /**
     * orgCode逻辑名
     */
    public static final String NAME_ORG_CODE = "机构代码";

    /**
     * orgName逻辑名
     */
    public static final String NAME_ORG_NAME = "机构名称";

    /**
     * orgType逻辑名
     */
    public static final String NAME_ORG_TYPE = "机构类型";

    /**
     * orgDegree逻辑名
     */
    public static final String NAME_ORG_DEGREE = "机构等级";

    /**
     * orgStatus逻辑名
     */
    public static final String NAME_ORG_STATUS = "机构状态";

    /**
     * guidParents逻辑名
     */
    public static final String NAME_GUID_PARENTS = "父机构GUID";

    /**
     * orgSeq逻辑名
     */
    public static final String NAME_ORG_SEQ = "机构序列";

    /**
     * orgAddr逻辑名
     */
    public static final String NAME_ORG_ADDR = "机构地址";

    /**
     * linkMan逻辑名
     */
    public static final String NAME_LINK_MAN = "联系人姓名";

    /**
     * linkTel逻辑名
     */
    public static final String NAME_LINK_TEL = "联系电话";

    /**
     * startDate逻辑名
     */
    public static final String NAME_START_DATE = "生效日期";

    /**
     * endDate逻辑名
     */
    public static final String NAME_END_DATE = "失效日期";

    /**
     * area逻辑名
     */
    public static final String NAME_AREA = "所属地域";

    /**
     * sortNo逻辑名
     */
    public static final String NAME_SORT_NO = "排列顺序编号";

    /**
     * isleaf逻辑名
     */
    public static final String NAME_ISLEAF = "是否叶子节点";

    /**
     * remark逻辑名
     */
    public static final String NAME_REMARK = "备注";

    /**
     * 数据主键:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    @TableId
    private String guid;

    /**
     * 机构代码:业务上对机构实体的编码。
     * 一般根据机构等级和机构类型进行有规则的编码。
     */
    private String orgCode;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 机构类型:见业务字典： DICT_OM_ORGTYPE
     * 如：总公司/总部部门/分公司/分公司部门...
     */
    private String orgType;

    /**
     * 机构等级:见业务字典： DICT_OM_ORGDEGREE
     * 如：总行，分行，海外分行...
     */
    private String orgDegree;

    /**
     * 机构状态:见业务字典： DICT_OM_ORGSTATUS
     */
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OmOrgStatus orgStatus ;

    /**
     * 父机构GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidParents;

    /**
     * 机构序列:类似面包屑导航，以“.”分割所有父机构GUID。能看出本机构的层级关系；
     * 格式： 父机构GUID.父机构GUID....本机构GUID
     */
    private String orgSeq;

    /**
     * 机构地址
     */
    private String orgAddr;

    /**
     * 联系人姓名
     */
    private String linkMan;

    /**
     * 联系电话
     */
    private String linkTel;

    /**
     * 生效日期
     */
    private Date startDate;

    /**
     * 失效日期
     */
    private Date endDate;

    /**
     * 所属地域:见业务字典： DICT_SD_AREA
     */
    private String area;

    /**
     * 排列顺序编号:维护时，可手工指定从0开始的自然数字；如果为空，系统将按照机构代码排序。
     */
    private BigDecimal sortNo;

    /**
     * 是否叶子节点:系统根据当前是否有下级机构判断更新（见业务字典 DICT_YON）
     */
    @JSONField(deserializeUsing= CommonEnumDeserializer.class)
    private YON isleaf ;

    /**
     * 备注
     */
    private String remark;

}

