package org.tis.tools.abf.module.om.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * 岗位状态:见业务字典： DICT_OM_POSISTATUS
 * Created by chenchao
 * Created on 2018/6/5 17:41
 */
public enum OmPositionStatus implements BaseEnum {

    /** 岗位状态：正常 */
    RUNNING("running", "正常"),

    /** 岗位状态：注销 */
    CANCEL("cancel", "注销");

    private final String value;

    private final String name;

    OmPositionStatus(final String value, final String name) {
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
