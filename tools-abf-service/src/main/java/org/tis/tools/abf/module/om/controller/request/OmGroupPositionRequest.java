package org.tis.tools.abf.module.om.controller.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.entity.request.RestRequest;

import java.util.List;

@Data
public class OmGroupPositionRequest extends RestRequest {

    @NotBlank(message = "工作组编号不能为空")
    private String groupCode;

    private List<String> posGuidlist;
}
