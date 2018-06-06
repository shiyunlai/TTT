package org.tis.tools.abf.module.om.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * 证件类型
 * Created by chenchao
 * Created on 2018/6/6 10:24
 */
public enum OmPaperType implements BaseEnum {

    /** 证件类型：身份证 */
    IDCARD("01", "身份证"),

    /** 证件类型：户口薄 */
    ACCOUNT("02", "户口薄"),

    /** 证件类型：军官证 */
    OFFICERCARD("03", "军官证"),

    /** 证件类型：学生证 */
    STUDENTIDCARD("04", "学生证"),

    /** 证件类型：护照 */
    PASSPORT("05", "护照"),

    /** 证件类型：其他 */
    OTHERS("06", "其他");

    private final String value;

    private final String name;

    OmPaperType(final String value, final String name) {
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
