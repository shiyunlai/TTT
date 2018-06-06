package org.tis.tools.abf.module.om.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.om.entity.enums.OmPositionStatus;
import org.tis.tools.abf.module.om.entity.enums.OmPositionType;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * omPosition岗位定义表
 * 岗位是职务在机构上的实例化表现（某个机构／部门中对某个职务（Responsibility）的工作定义）；
 * 一般情况下，每个岗位都需要配置一个职务系统当然出于业务上扩展考虑，并没有限制岗位一定要对应上职务；
 * 例如，一个公司有三个部门A，B，C，每个部门都设置了管理岗位 A部门经理，B部门经理，C部门经理。同时可在三个岗位上设置共同的职务为“经理”
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("om_position")
public class OmPosition implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "岗位";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidOrg对应表字段
     */
    public static final String COLUMN_GUID_ORG = "guid_org";

    /**
     * positionCode对应表字段
     */
    public static final String COLUMN_POSITION_CODE = "position_code";

    /**
     * positionName对应表字段
     */
    public static final String COLUMN_POSITION_NAME = "position_name";

    /**
     * positionType对应表字段
     */
    public static final String COLUMN_POSITION_TYPE = "position_type";

    /**
     * positionStatus对应表字段
     */
    public static final String COLUMN_POSITION_STATUS = "position_status";

    /**
     * isleaf对应表字段
     */
    public static final String COLUMN_ISLEAF = "isleaf";

    /**
     * subCount对应表字段
     */
    public static final String COLUMN_SUB_COUNT = "sub_count";

    /**
     * positionLevel对应表字段
     */
    public static final String COLUMN_POSITION_LEVEL = "position_level";

    /**
     * positionSeq对应表字段
     */
    public static final String COLUMN_POSITION_SEQ = "position_seq";

    /**
     * guidParents对应表字段
     */
    public static final String COLUMN_GUID_PARENTS = "guid_parents";

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
     * guidOrg逻辑名
     */
    public static final String NAME_GUID_ORG = "隶属机构GUID";

    /**
     * positionCode逻辑名
     */
    public static final String NAME_POSITION_CODE = "岗位代码";

    /**
     * positionName逻辑名
     */
    public static final String NAME_POSITION_NAME = "岗位名称";

    /**
     * positionType逻辑名
     */
    public static final String NAME_POSITION_TYPE = "岗位类别";

    /**
     * positionStatus逻辑名
     */
    public static final String NAME_POSITION_STATUS = "岗位状态";

    /**
     * isleaf逻辑名
     */
    public static final String NAME_ISLEAF = "是否叶子岗位";

    /**
     * subCount逻辑名
     */
    public static final String NAME_SUB_COUNT = "子节点数";

    /**
     * positionLevel逻辑名
     */
    public static final String NAME_POSITION_LEVEL = "岗位层次";

    /**
     * positionSeq逻辑名
     */
    public static final String NAME_POSITION_SEQ = "岗位序列";

    /**
     * guidParents逻辑名
     */
    public static final String NAME_GUID_PARENTS = "父岗位GUID";

    /**
     * startDate逻辑名
     */
    public static final String NAME_START_DATE = "岗位有效开始日期";

    /**
     * endDate逻辑名
     */
    public static final String NAME_END_DATE = "岗位有效截止日期";

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
     * 隶属机构GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidOrg;

    /**
     * 岗位代码:业务上对岗位的编码
     */
    private String positionCode;

    /**
     * 岗位名称
     */
    private String positionName;

    /**
     * 岗位类别:见业务字典： DICT_OM_POSITYPE
     * 机构岗位，工作组岗位
     */
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OmPositionType positionType;

    /**
     * 岗位状态:见业务字典： DICT_OM_POSISTATUS
     */
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OmPositionStatus positionStatus;

    /**
     * 是否叶子岗位:见业务字典： DICT_YON
     */
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private YON isleaf;

    /**
     * 子节点数
     */
    private BigDecimal subCount;

    /**
     * 岗位层次
     */
    private BigDecimal positionLevel;

    /**
     * 岗位序列:岗位的面包屑定位信息
     */
    private String positionSeq;

    /**
     * 父岗位GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidParents;

    /**
     * 岗位有效开始日期
     */
    private Date startDate;

    /**
     * 岗位有效截止日期
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

