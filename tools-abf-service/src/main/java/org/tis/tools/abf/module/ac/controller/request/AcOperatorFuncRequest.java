package org.tis.tools.abf.module.ac.controller.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.ac.entity.enums.AcAuthType;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.core.entity.request.RestRequest;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by chenchao
 * Created on 2018/6/11 9:23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcOperatorFuncRequest extends RestRequest {

    private String guid;

    @NotBlank(message = "操作员GUID不能为空")
    private String guidOperator;

    @NotBlank(message = "功能GUID不能为空")
    private String guidFunc;

    @NotNull(message = "授权标志不能为空")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private AcAuthType authType;

    private Date startDate;

    private Date endDate;

    private String guidApp;
}
