package org.tis.tools.abf.module.om.controller.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.tis.tools.core.entity.request.RestRequest;

import java.util.List;

@Data
public class OmGroupAddAppRequest extends RestRequest {

    @NotBlank(message = "工作组的groupGuid不能为空")
    private String groupCode;

    @NotEmpty
    private List<String> appGuidList;
}
