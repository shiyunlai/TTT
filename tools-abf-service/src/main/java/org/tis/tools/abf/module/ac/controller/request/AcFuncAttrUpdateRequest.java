package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.model.web.RestRequest;

/**
 * Created by chenchao
 * Created on 2018/5/22 10:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcFuncAttrUpdateRequest extends RestRequest{

    @NotBlank(message = "GUID不能为空")
    private String guid;

    @NotBlank(message = "对应功能GUID不能为空")
    private String guidFunc;

    private String attrType;

    private String attrKey;

    private String attrValue;

    private String memo;
}
