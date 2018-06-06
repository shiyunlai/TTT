package org.tis.tools.abf.module.om.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * 岗位类别:见业务字典： DICT_OM_POSITYPE
 * Created by chenchao
 * Created on 2018/6/5 17:36
 */
public enum OmPositionType implements BaseEnum {

    /** 岗位类别：机构岗位 */
    ORGANIZATION("01", "机构岗位"),

    /** 岗位类别：工作组岗位 */
    WORKINGGROUP("02", "工作组岗位");

    private final String value;

    private final String name;

    OmPositionType(final String value, final String name) {
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
