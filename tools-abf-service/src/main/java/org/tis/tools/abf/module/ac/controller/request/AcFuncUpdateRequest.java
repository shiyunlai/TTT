package org.tis.tools.abf.module.ac.controller.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.ac.entity.enums.FuncType;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.model.web.RestRequest;

import javax.validation.constraints.NotNull;

/**
 * Created by chenchao
 * Created on 2018/5/15 9:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcFuncUpdateRequest extends RestRequest {

    private String guid;

    @NotBlank(message = "隶属应用ID不能为空")
    private String guidApp;

    @NotNull(message = "功能类型不能为空")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private FuncType funcType;

    @NotBlank(message = "功能编号不能为空")
    private String funcCode;

    @NotBlank(message = "功能名称不能为空")
    private String funcName;

    @NotBlank(message = "显示顺序不能为空")
    private String displayOrder;

    private String guidFunc;

    @NotNull(message = "是否启用不能为空")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private YON isopen;

    @NotNull(message = "是否验证权限不能为空")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private YON ischeck;

    private String funcDesc;

}
