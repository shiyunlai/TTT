package org.tis.tools.abf.module.om.controller.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.model.web.RestRequest;

@Data
public class OmGroupUpdateRequest extends RestRequest {

    @NotBlank(message = "工作组的code不能为空")
    private String groupCode;

    private String groupName;

    private String groupDesc;

    @NotBlank(message = "工作组隶属机构不能为空")
    private String guidOrg;

}
