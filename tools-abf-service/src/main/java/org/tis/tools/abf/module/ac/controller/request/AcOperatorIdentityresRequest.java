package org.tis.tools.abf.module.ac.controller.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.ac.entity.enums.AcResourceType;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.core.entity.request.RestRequest;

import javax.validation.constraints.NotNull;

/**
 * Created by chenchao
 * Created on 2018/6/8 15:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcOperatorIdentityresRequest extends RestRequest {

    private String guid;

    @NotBlank(message = "身份GUID不能为空")
    private String guidIdentity;

    @NotNull(message = "资源类型不能为空")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private AcResourceType acResourcetype;

    @NotBlank(message = "资源GUID不能为空")
    private String guidAcResource;

}
