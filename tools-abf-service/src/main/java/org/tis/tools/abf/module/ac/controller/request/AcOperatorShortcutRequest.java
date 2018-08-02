package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.model.web.RestRequest;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by chenchao
 * Created on 2018/6/8 9:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcOperatorShortcutRequest extends RestRequest {

    private String guid;

    @NotBlank(message = "操作员GUID不能为空")
    private String guidOperator;

    @NotBlank(message = "快捷按键不能为空")
    private String shortcutKey;

    @NotBlank(message = "应用GUID不能为空")
    private String guidApp;

    @NotBlank(message = "功能GUID不能为空")
    private String guidFunc;

    private String aliasFuncName;

    @NotNull(message ="排列顺序不能为空")
    private BigDecimal orderNo;

    private String imagePath;

    private String expandPath;

}
