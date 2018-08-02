package org.tis.tools.abf.module.ac.controller.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.ac.entity.enums.AcAppType;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.model.web.RestRequest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by chenchao
 * Created on 2018/5/2 15:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcAppUpdateRequest extends RestRequest{


    @NotBlank(message = "应用代码不能为空")
    private String appCode;

    @NotBlank(message = "应用名称不能为空")
    private String appName;

    @NotNull(message = "应用类型不能为空")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private AcAppType appType;

    @NotNull(message = "是否开通不能为空")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private YON isopen;

    private String guid;

    private String openDate;

    @Pattern(regexp = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]",message = "访问地址不符合标准")
    private String url;

    private String ipAddr;

    private String ipPort;

    private String appDesc;


}
