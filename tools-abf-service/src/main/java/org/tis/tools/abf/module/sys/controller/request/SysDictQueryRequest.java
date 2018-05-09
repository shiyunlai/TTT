package org.tis.tools.abf.module.sys.controller.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.entity.request.RestRequest;
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
    private String seqNo;
    private String sqlFilter;
    private String fromType;
}
