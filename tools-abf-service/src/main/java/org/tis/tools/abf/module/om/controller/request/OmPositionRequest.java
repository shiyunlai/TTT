package org.tis.tools.abf.module.om.controller.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.om.entity.enums.OmPositionStatus;
import org.tis.tools.abf.module.om.entity.enums.OmPositionType;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.core.entity.request.RestRequest;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by chenchao
 * Created on 2018/6/5 17:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OmPositionRequest extends RestRequest {

    private String guid;

    @NotBlank(message = "隶属机构GUID不能为空")
    private String guidOrg;

    @NotBlank(message = "岗位代码不能为空")
    private String positionCode;

    @NotBlank(message = "岗位名称不能为空")
    private String positionName;

    @NotNull(message = "岗位类别不能为空")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OmPositionType positionType;

    @NotNull(message = "岗位状态不能为空")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OmPositionStatus positionStatus;

    @NotNull(message = "是否叶子岗位不能为空")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private YON isleaf;

    @NotBlank(message = "子节点数不能为空")
    private BigDecimal subCount;

    private BigDecimal positionLevel;

    private String positionSeq;

    private String guidParents;

    private Date startDate;

    private Date endDate;
}
