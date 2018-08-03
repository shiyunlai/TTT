package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.model.web.RestRequest;

import java.util.List;

/**
 * Created by chenchao
 * Created on 2018/7/30 10:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcOperatorRoleBatchAddRequest extends RestRequest {

    @NotBlank(message = "角色ID不能为空")
    private String roleId;

    private List<String> list;

}
