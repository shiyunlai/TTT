package org.tis.tools.abf.module.ac.controller.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.core.entity.request.RestRequest;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by chenchao
 * Created on 2018/6/8 14:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcOperatorIdentityRequest extends RestRequest {

    private String guid;

    @NotBlank(message = "操作员GUID不能为空")
    private String guidOperator;

    @NotBlank(message = "身份名称不能为空")
    private String identityName;

    @NotNull(message = "默认身份标志不能为空")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private YON identityFlag;

    private String guidApp;

    private BigDecimal seqNo;

}
