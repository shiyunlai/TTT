package org.tis.tools.abf.module.om.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * 性别
 * Created by chenchao
 * Created on 2018/6/6 10:15
 */
public enum OmGender implements BaseEnum {

    /** 性别：男 */
    M("M", "男"),

    /** 性别：女 */
    F("F", "女"),

    /** 性别：未知 */
    U("U", "未知");

    private final String value;

    private final String name;

    OmGender(final String value, final String name) {
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
