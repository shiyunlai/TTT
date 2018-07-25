package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.entity.request.RestRequest;


/**
 *@author wfl
 * @date 2018/05/09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcRoleFuncAddRequest extends RestRequest {

    private String guid;

    @NotBlank(message = "角色GUID不能为空")
    private String guidRole;

    @NotBlank(message = "功能GUID不能为空")
    private String guidFunc;

    @NotBlank(message = "应用GUID不能为空")
    private String guidApp;
}
