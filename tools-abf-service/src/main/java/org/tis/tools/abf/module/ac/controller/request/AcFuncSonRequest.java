package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.entity.request.RestRequest;

/**
 * Created by chenchao
 * Created on 2018/5/15 9:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcFuncSonRequest extends RestRequest {

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

    private String guidFunc;

    private String funcDesc;

    private String isopen;

    private String ischeck;
}
