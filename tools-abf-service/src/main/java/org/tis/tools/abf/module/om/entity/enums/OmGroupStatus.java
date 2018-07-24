package org.tis.tools.abf.module.om.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * describe: 工作组状态枚举类
 *
 * @author lijh
 * @date 2018/6/14
 **/
public enum OmGroupStatus implements BaseEnum {

    /** 工作组状态：正常 */
    RUNNING("running", "正常"),

    /** 工作组状态：注销 */
    ANCEL("cancel", "注销");

    private final String value;

    private final String name;

    OmGroupStatus(final String value, final String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }


    @Override
    public Serializable deserialze() {
        return value;
    }

}
