package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.entity.request.RestRequest;


/**
 * Created by chenchao
 * Created on 2018/5/3 9:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcAppConfigAddRequest extends RestRequest {

        private String guid;

        @NotBlank(message = "应用GUID不能为空")
        private String guidApp;

        @NotBlank(message = "配置类型不能为空")
        private String configType;

        @NotBlank(message = "配置名不能为空")
        private String configName;

        @NotBlank(message = "配置值字典不能为空")
        private String configDict;

        private String configStyle;

        @NotBlank(message = "默认配置值不能为空")
        private String configValue;

        private String enabled;

        private String displayOrder;

        private String configDesc;

}
