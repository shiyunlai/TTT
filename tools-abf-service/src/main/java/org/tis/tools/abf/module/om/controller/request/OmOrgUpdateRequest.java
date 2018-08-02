package org.tis.tools.abf.module.om.controller.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.om.entity.enums.OmOrgArea;
import org.tis.tools.abf.module.om.entity.enums.OmOrgDegree;
import org.tis.tools.abf.module.om.entity.enums.OmOrgStatus;
import org.tis.tools.abf.module.om.entity.enums.OmOrgType;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.model.web.RestRequest;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by chenchao
 * Created on 2018/6/5 14:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OmOrgUpdateRequest extends RestRequest {

    private String guid;

    @NotBlank(message = "机构代码不能为空")
    private String orgCode;

    @NotBlank(message = "机构名称不能为空")
    private String orgName;

    @NotNull(message = "机构类型不能为空")
    @JSONField(deserializeUsing= CommonEnumDeserializer.class)
    private OmOrgType orgType;

    @NotNull(message = "机构等级不能为空")
    @JSONField(deserializeUsing= CommonEnumDeserializer.class)
    private OmOrgDegree orgDegree;

    @NotNull(message = "机构状态不能为空")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OmOrgStatus orgStatus ;

    private String guidParents;

    private String orgSeq;

    private String orgAddr;

    private String linkMan;

    private String linkTel;

    private Date startDate;

    private Date endDate;

    @JSONField(deserializeUsing= CommonEnumDeserializer.class)
    private OmOrgArea area;

    private BigDecimal sortNo;

    @NotNull(message = "是否叶子节点不能为空")
    @JSONField(deserializeUsing= CommonEnumDeserializer.class)
    private YON isleaf ;

    private String remark;
}
