package org.tis.tools.abf.module.om.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * 人员状态
 * Created by chenchao
 * Created on 2018/6/6 10:20
 */
public enum OmEmployeeStatus implements BaseEnum {

    /** 人员状态：在招 */
    OFFER("offer", "在招"),

    /** 人员状态：在职 */
    ONJOB("onjob", "在职"),

    /** 人员状态：离职 */
    OFFJOB("offjob", "离职");

    private final String value;

    private final String name;

    OmEmployeeStatus(final String value, final String name) {
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
