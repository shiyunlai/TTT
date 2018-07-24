package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by chenchao
 * Created on 2018/6/28 9:46
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcOperatorDefaultIdentity {

    @NotBlank(message = "操作员身份GUID不能为空")
    private String guid;

    @NotBlank(message = "操作员GUID不能为空")
    private String guidOperator;


}
