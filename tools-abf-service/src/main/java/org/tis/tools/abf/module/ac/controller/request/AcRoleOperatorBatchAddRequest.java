package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.model.web.RestRequest;

import java.util.List;

/**
 * Created by chenchao
 * Created on 2018/8/2 15:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcRoleOperatorBatchAddRequest extends RestRequest {

    @NotBlank(message = "操作员ID不能为空")
    private String operatorId;

    private List<String> list;

}
