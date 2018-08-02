package org.tis.tools.abf.module.sys.controller.request;

import lombok.Data;
import org.tis.tools.model.web.RestRequest;

import java.math.BigDecimal;

@Data
public class SysDictQueryRequest extends RestRequest {
    private String guid;
    private String dictKey;
    private String dictType;
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
