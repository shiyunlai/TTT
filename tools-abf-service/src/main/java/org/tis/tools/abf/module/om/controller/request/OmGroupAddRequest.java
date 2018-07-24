package org.tis.tools.abf.module.om.controller.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.om.entity.enums.OmGroupType;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.core.entity.request.RestRequest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class OmGroupAddRequest extends RestRequest {

    public interface Root{}
    public interface Child{}

    @NotBlank(message = "工作组名不能为空")
    private String groupName;

    @NotNull(message = "工作组类型不能为空或类型不存在")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OmGroupType groupType;

    @NotBlank(message = "隶属机构guid不能为空")
    private String guidOrg;

    @Null(message = "根工作组父GUID为空！", groups = {OmGroupAddRequest.Root.class})
    @NotBlank(message = "根工作组父GUID不能为空！", groups = {OmGroupAddRequest.Child.class})
    private String guidParents;

    private String groupDesc;
}
