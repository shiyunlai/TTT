package org.tis.tools.abf.module.jnl.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.entity.request.RestRequest;

/**
 * Created by chenchao
 * Created on 2018/6/4 8:59
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LogAbfOperateRequest extends RestRequest{

    @NotBlank(message = "操作时间不能为空")
    private String operateTime;

    private String userId;

}
