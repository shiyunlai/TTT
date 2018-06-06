package org.tis.tools.abf.module.om.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * 所属区域
 * Created by chenchao
 * Created on 2018/6/5 13:59
 */
public enum OmOrgArea implements BaseEnum {

    /** 所属区域：北京 */
    BEIJING("010", "北京"),

    /** 所属区域：上海 */
    SHANGHAI("021", "上海");

    private final String value;

    private final String name;

    OmOrgArea(final String value, final String name) {
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
