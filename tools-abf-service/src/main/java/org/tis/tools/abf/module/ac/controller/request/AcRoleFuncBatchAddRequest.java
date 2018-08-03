package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.ac.entity.vo.OperatorRoleAdd;
import org.tis.tools.model.web.RestRequest;

import java.util.Map;

/**
 * Created by chenchao
 * Created on 2018/7/26 9:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcRoleFuncBatchAddRequest extends RestRequest {

    @NotBlank(message = "角色ID不能为空")
    private String roleId;

    private Map<String,OperatorRoleAdd> batchData;
}
