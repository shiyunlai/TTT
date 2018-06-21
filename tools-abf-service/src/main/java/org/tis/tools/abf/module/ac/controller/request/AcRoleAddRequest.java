package org.tis.tools.abf.module.ac.controller.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.core.entity.request.RestRequest;

import javax.validation.constraints.NotNull;

/**
*@author wfl
* @date 2018/05/09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcRoleAddRequest extends RestRequest {

    @NotBlank(message = "角色代码不能为空")
    private String roleCode;

    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    @JSONField(deserializeUsing= CommonEnumDeserializer.class)
    @NotNull(message = "是否启用不能为空")
    private YON enabled;

    private String roleDesc;

    private String roleGroup;

}
