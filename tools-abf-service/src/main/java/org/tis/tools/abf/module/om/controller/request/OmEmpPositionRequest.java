package org.tis.tools.abf.module.om.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.core.entity.request.RestRequest;

/**
 * Created by chenchao
 * Created on 2018/6/15 11:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OmEmpPositionRequest extends RestRequest {

    private String guid;

    @NotBlank(message = "员工GUID不能为空")
    private String guidEmp;

    @NotBlank(message = "所在岗位GUID不能为空")
    private String guidPosition;

    @NotBlank(message = "是否主岗位不能为空")
    private YON ismain;

}
