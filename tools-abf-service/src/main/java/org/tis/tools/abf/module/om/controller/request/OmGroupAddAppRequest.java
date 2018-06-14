package org.tis.tools.abf.module.om.controller.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.entity.request.RestRequest;

@Data
public class OmGroupAddAppRequest extends RestRequest {

    @NotBlank(message = "工作组的groupGuid不能为空")
    private String groupGuid;

    @NotBlank(message = "工作组的appGuid不能为空")
    private String appGuid;
}
