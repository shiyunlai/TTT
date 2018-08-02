package org.tis.tools.abf.module.om.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.tis.tools.model.web.RestRequest;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by chenchao
 * Created on 2018/6/5 15:01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OmOrgSetDateRequest extends RestRequest{

    private String guid;

    @NotNull(message = "生效日期不能为空")
    private Date startDate;

    @NotNull(message = "失效日期不能为空")
    private Date endDate;


}
