package org.tis.tools.abf.module.sys.controller.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.common.entity.request.RestRequest;
@Data
public class SysRunConfigQueryRequest extends RestRequest{
        private String guid;
}