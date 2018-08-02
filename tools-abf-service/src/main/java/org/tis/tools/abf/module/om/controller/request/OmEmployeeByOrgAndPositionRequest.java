package org.tis.tools.abf.module.om.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.model.web.RestRequest;

/**
 * 该request类是为了查询机构和岗位下的员工而存在
 * Created by chenchao
 * Created on 2018/6/13 17:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OmEmployeeByOrgAndPositionRequest extends RestRequest {

    @NotBlank(message = "主机构编号不能为空")
    private String guidOrg;

    @NotBlank(message = "基本岗位不能为空")
    private String guidPosition;
}
