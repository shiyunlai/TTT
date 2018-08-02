package org.tis.tools.abf.module.om.controller.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.model.web.RestRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class OmGroupPositionRequest extends RestRequest {

    @NotBlank(message = "工作组编号不能为空")
    private String groupCode;

    @NotNull(message = "岗位信息不能为空")
    @Valid
    private OmPositionRequest omPositionRequest;
}
