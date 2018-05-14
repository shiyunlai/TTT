package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.entity.request.RestRequest;

import java.util.Date;

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

    @NotBlank(message = "应用类型不能为空")
    private String appType;

    @NotBlank(message = "是否开通不能为空")
    private String isopen;

    private String guid;

    private Date openDate;

    //@Pattern(regexp = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]",message = "访问地址不符合标准")
    private String url;

    private String ipAddr;

    private String ipPort;

    private String appDesc;


}
