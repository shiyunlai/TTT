package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.model.web.RestRequest;

/**
 * Created by chenchao
 * Created on 2018/8/2 8:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcOperatorFuncQueConditionRequest extends RestRequest {

    @NotBlank(message = "操作员ID不能为空")
    private String operatorId;

    private String appId;

    private String funcId;

}
