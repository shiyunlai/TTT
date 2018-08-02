package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.model.web.RestRequest;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by chenchao
 * Created on 2018/6/11 10:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcOperatorFuncDateRequest extends RestRequest {

    @NotBlank(message = "GUID不能为空")
    private String guid;

    @NotNull(message = "有效开始日期不能为空")
    private Date startDate;

    @NotNull(message = "有效截止日期不能为空")
    private Date endDate;
}
