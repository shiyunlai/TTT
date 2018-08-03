package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.model.web.RestRequest;

/**
 * Created by chenchao
 * Created on 2018/8/1 9:56
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcRoleFuncQueConditionRequest extends RestRequest {

    @NotBlank(message = "角色ID不能为空")
    private String roleId;

    private String appId;

    private String funcId;

}
