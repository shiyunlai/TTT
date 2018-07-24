package org.tis.tools.abf.module.om.controller.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.om.entity.enums.OmOrgArea;
import org.tis.tools.abf.module.om.entity.enums.OmOrgDegree;
import org.tis.tools.abf.module.om.entity.enums.OmOrgType;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.core.entity.request.RestRequest;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class OmOrgAddRequest extends RestRequest {

    @NotNull(message = "区域代码不能为空")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OmOrgArea area;

    @NotNull(message = "机构等级不能为空")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OmOrgDegree orgDegree;

    @NotBlank(message = "机构名称不能为空")
    private String orgName;

    @NotNull(message = "机构类型不能为空")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OmOrgType orgType;

    private String orgAddr;

    private String linkMan;

    private String linkTel;

    private Date startDate;

    private Date endDate;

    private String remark;

    private String guidParents;
}
