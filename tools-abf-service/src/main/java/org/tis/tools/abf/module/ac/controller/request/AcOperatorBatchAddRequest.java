package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.ac.entity.vo.OperatorRoleAdd;
import org.tis.tools.model.web.RestRequest;

import java.util.Map;

/**
 * Created by chenchao
 * Created on 2018/8/2 17:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcOperatorBatchAddRequest extends RestRequest {

    @NotBlank(message = "操作员ID不能为空")
    private String operatorId;

    private Map<String,OperatorRoleAdd> batchData;
}
