package org.tis.tools.abf.module.om.controller.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.entity.request.RestRequest;

@Data
public class OmGroupAddEmpRequest extends RestRequest {

    @NotBlank(message = "工作组的guidGroup不能为空")
    private String guidGroup;

    @NotBlank(message = "员工的guid不能为空")
    private String guidEmp;
}
