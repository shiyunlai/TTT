package org.tis.tools.abf.module.om.controller.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.core.entity.request.RestRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OmGroupPositionRequest extends RestRequest {

    @NotBlank(message = "工作组编号不能为空")
    private String groupCode;

    @NotNull(message = "岗位信息不能为空")
    @Valid
    private OmPositionRequest omPositionRequest;
}
