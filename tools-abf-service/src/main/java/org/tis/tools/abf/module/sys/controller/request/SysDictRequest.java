package org.tis.tools.abf.module.sys.controller.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.entity.request.RestRequest;

import java.math.BigDecimal;

@Data
public class SysDictRequest extends RestRequest {
    private String guid;
    @NotBlank(message = "业务字典不能为空")
    private String dictKey;
    @NotBlank(message = "类型典不能为空")
    private String dictType;
    @NotBlank(message = "字典名称不能为空")
    private String dictName;
    private String dictDesc;
    private String guidParents;
    private String defaultValue;
    private String fromTable;
    private String useForKey;
    private String useForName;
    private BigDecimal seqNo;
    private String sqlFilter;
    private String fromType;
}
