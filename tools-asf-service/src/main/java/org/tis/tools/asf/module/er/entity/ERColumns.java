package org.tis.tools.asf.module.er.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class ERColumns {

    @JSONField(name = "normal_column")
    private List<ERColumn> normalColumnList;

    @JSONField(name = "column_group")
    private List<String> columnGroupList;
}
