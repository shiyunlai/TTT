package org.tis.tools.abf.module.om.controller.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.core.entity.request.RestRequest;

import javax.validation.constraints.NotNull;

/**
 * Created by chenchao
 * Created on 2018/6/15 11:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OmEmpPositionRequest extends RestRequest {

    private String guid;

    @NotBlank(message = "员工GUID不能为空")
    private String guidEmp;

    @NotBlank(message = "所在岗位GUID不能为空")
    private String guidPosition;

    @NotNull(message = "是否主岗位不能为空")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private YON ismain;

}
