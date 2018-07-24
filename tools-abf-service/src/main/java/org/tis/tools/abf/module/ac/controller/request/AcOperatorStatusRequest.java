package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.ac.entity.enums.OperatorStatus;
import org.tis.tools.core.entity.request.RestRequest;

import javax.validation.constraints.NotNull;

/**
 * Created by chenchao
 * Created on 2018/6/12 15:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcOperatorStatusRequest extends RestRequest {

    @NotBlank(message = "guid不能为空!")
    private String guid;

    @NotNull(message = "操作员状态不能为空!")
    private OperatorStatus operatorStatus;

}
