package org.tis.tools.abf.module.sys.controller.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.entity.request.RestRequest;
@Data
public class SysRunConfigAddRequest  extends RestRequest {
    @NotBlank(message = "应用系统不能为空")
    private String guidApp;
    @NotBlank(message = "参数组别不能为空")
    private String groupName;
    @NotBlank(message = "参数键名称不能为空")
    private String keyName;
    @NotBlank(message = "值来源类型不能为空")
    private String valueFrom;
    @NotBlank(message = "参数值不能为空")
    private String value;
    private String description;
}