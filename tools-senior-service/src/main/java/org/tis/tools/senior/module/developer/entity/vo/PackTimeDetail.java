package org.tis.tools.senior.module.developer.entity.vo;

import lombok.Data;
import org.tis.tools.senior.module.developer.entity.enums.OptionsPackTime;

@Data
public class PackTimeDetail {

    private OptionsPackTime isOptions;

    private String packTime;

}
