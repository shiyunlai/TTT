package org.tis.tools.abf.module.om.controller.request;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.om.entity.OmEmployee;
import org.tis.tools.core.entity.request.RestRequest;

import javax.validation.constraints.NotNull;

@Data
public class OmGroupUpdateRequest extends RestRequest {

    @NotBlank(message = "工作组的code不能为空")
    private String groupCode;

    private String groupName;

    private String groupDesc;

    @NotBlank(message = "工作组隶属机构不能为空")
    private String groupOrg;

    @NotNull(message = "分页信息不能为空")
    Page<OmEmployee> page;
}
