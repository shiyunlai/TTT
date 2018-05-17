package org.tis.tools.abf.module.ac.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * acOperatorIdentity操作员对自己的权限进行组合形成一个固定的登录身份；
 * 供登录时选项，每一个登录身份是员工操作员的权限子集；
 * 登陆应用系统时，可以在权限子集间选择，如果不指定，则采用默认身份登陆。
 * （可基于本表扩展支持：根据登陆渠道返回操作员的权限）
 * 可以没有身份，不指定身份登陆时，表示使用操作所有角色、功能权限
 * 
 * @author Auto Generate Tools
 * @date 2018/05/17
 */
@Data
@TableName("ac_operator_identity")
public class AcOperatorIdentity implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    public static final String NAME = "操作员身份";

    /**
     * guid对应表字段
     */
    public static final String COLUMN_GUID = "guid";

    /**
     * guidOperator对应表字段
     */
    public static final String COLUMN_GUID_OPERATOR = "guid_operator";

    /**
     * identityName对应表字段
     */
    public static final String COLUMN_IDENTITY_NAME = "identity_name";

    /**
     * identityFlag对应表字段
     */
    public static final String COLUMN_IDENTITY_FLAG = "identity_flag";

    /**
     * guidApp对应表字段
     */
    public static final String COLUMN_GUID_APP = "guid_app";

    /**
     * seqNo对应表字段
     */
    public static final String COLUMN_SEQ_NO = "seq_no";

    /**
     * guid逻辑名
     */
    public static final String NAME_GUID = "数据主键";

    /**
     * guidOperator逻辑名
     */
    public static final String NAME_GUID_OPERATOR = "操作员GUID";

    /**
     * identityName逻辑名
     */
    public static final String NAME_IDENTITY_NAME = "身份名称";

    /**
     * identityFlag逻辑名
     */
    public static final String NAME_IDENTITY_FLAG = "默认身份标志";

    /**
     * guidApp逻辑名
     */
    public static final String NAME_GUID_APP = "适用于应用";

    /**
     * seqNo逻辑名
     */
    public static final String NAME_SEQ_NO = "显示顺序";

    /**
     * 数据主键:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    @TableId
    private String guid;

    /**
     * 操作员GUID:全局唯一标识符（GUID，Globally Unique Identifier），系统自动生成；
     */
    private String guidOperator;

    /**
     * 身份名称
     */
    private String identityName;

    /**
     * 默认身份标志:见业务字典： DICT_YON
     * 只能有一个默认身份 Y是默认身份 N不是默认身份
     */
    private String identityFlag;

    /**
     * 适用于应用
     */
    private String guidApp;

    /**
     * 显示顺序
     */
    private BigDecimal seqNo;

}

