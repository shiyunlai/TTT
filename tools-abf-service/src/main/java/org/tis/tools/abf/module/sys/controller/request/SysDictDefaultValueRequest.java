package org.tis.tools.abf.module.sys.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.entity.request.RestRequest;

/**
 * Created by chenchao
 * Created on 2018/6/12 9:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDictDefaultValueRequest extends RestRequest {

    @NotBlank(message = "GUID不能为空")
    private String guid;

    @NotBlank(message = "默认字典项不能为空")
    private String defaultValue;
}
