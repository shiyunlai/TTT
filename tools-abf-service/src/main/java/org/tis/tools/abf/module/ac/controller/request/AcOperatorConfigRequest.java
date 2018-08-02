package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.model.web.RestRequest;

/**
 * Created by chenchao
 * Created on 2018/6/11 10:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcOperatorConfigRequest extends RestRequest{

    private String guid;

    @NotBlank(message = "操作员GUID不能为空")
    private String guidOperator;

    @NotBlank(message = "配置GUID不能为空")
    private String guidConfig;

    private String configValue;

}
