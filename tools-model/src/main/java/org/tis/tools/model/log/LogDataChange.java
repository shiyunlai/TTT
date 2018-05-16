package org.tis.tools.model.log;

import lombok.Data;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/5/15
 **/
@Data
public class LogDataChange {

    /**
     * 变化项字段名
     */
    public String physicalName;

    /**
     * 变化项逻辑名称
     */
    public String logicName;

    /**
     * 变化之前值
     */
    public String oldValue;

    /**
     * 变化之后值
     */
    public String newValue;
}
