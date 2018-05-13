package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.entity.request.RestRequest;

@Data
public class AcRoleFuncDeleteRequest extends RestRequest {

    @NotBlank(message = "角色GUID不能为空")
    private String guidRole;

    private String guidFunc;

    private String guidApp;
}
