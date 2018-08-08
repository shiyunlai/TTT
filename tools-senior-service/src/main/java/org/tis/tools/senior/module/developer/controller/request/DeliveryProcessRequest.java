package org.tis.tools.senior.module.developer.controller.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.model.web.RestRequest;
import org.tis.tools.senior.module.developer.entity.enums.DeliveryResult;

import javax.validation.constraints.NotNull;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/7/1
 **/
@Data
public class DeliveryProcessRequest extends RestRequest {

    @NotNull(message = "投放结果不能为空或格式错误！")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private DeliveryResult result;

    private String desc;
}
