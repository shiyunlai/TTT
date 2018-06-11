package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.ac.entity.enums.AcOpenMode;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.core.entity.request.RestRequest;

import java.math.BigDecimal;

/**
 * Created by chenchao
 * Created on 2018/6/11 15:17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcOperatorMenuRequest extends RestRequest {

    private String guid;

    @NotBlank(message = "操作员GUID不能为空!")
    private String guidOperator;

    private String guidApp;

    private String guidFunc;

    @NotBlank(message = "菜单名称不能为空!")
    private String menuName;

    @NotBlank(message = "菜单显示(中文)不能为空!")
    private String menuLabel;

    private String guidParents;

    private YON isleaf;

    private String uiEntry;

    private BigDecimal menuLevel;

    private String guidRoot;

    private BigDecimal displayOrder;

    private String imagePath;

    private String expandPath;

    private String menuSeq;

    private AcOpenMode openMode;

    private BigDecimal subCount;

}
