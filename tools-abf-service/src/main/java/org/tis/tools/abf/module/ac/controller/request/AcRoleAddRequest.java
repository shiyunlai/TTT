package org.tis.tools.abf.module.ac.controller.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.model.web.RestRequest;

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
    private YON enabled;

    private String roleDesc;

    @NotBlank(message = "角色分组不能为空")
    private String roleGroup;

}
