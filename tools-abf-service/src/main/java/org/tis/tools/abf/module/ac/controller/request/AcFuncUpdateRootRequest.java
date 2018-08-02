package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.model.web.RestRequest;

/**
 * Created by chenchao
 * Created on 2018/5/15 16:35
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcFuncUpdateRootRequest extends RestRequest {
    private String guid;

    @NotBlank(message = "隶属应用ID不能为空")
    private String guidApp;

    @NotBlank(message = "功能类型不能为空")
    private String funcType;

    @NotBlank(message = "功能编号不能为空")
    private String funcCode;

    @NotBlank(message = "功能名称不能为空")
    private String funcName;

    @NotBlank(message = "显示顺序不能为空")
    private String displayOrder;

    @NotBlank(message = "是否启用不能为空")
    private String isopen;

    @NotBlank(message = "是否验证权限不能为空")
    private String ischeck;

    private String funcDesc;

    private String guidFunc;

}
