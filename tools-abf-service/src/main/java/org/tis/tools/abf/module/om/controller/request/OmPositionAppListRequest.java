package org.tis.tools.abf.module.om.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.model.web.RestRequest;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by chenchao
 * Created on 2018/6/14 15:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OmPositionAppListRequest extends RestRequest {

    @NotBlank(message = "岗位GUID不能为空")
    private String guidPosition;

    @NotNull(message = "应用列表不能为空")
    private List<String> appList;
}
