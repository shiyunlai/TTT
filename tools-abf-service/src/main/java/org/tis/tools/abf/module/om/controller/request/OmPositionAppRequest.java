package org.tis.tools.abf.module.om.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.entity.request.RestRequest;

/**
 * Created by chenchao
 * Created on 2018/6/7 14:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OmPositionAppRequest extends RestRequest {

    private String guid;

    @NotBlank(message = "岗位GUID不能为空")
    private String guidPosition;

    @NotBlank(message = "应用GUID不能为空")
    private String guidApp;

}
