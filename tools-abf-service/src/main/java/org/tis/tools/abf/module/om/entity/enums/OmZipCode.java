package org.tis.tools.abf.module.om.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * 邮编
 * Created by chenchao
 * Created on 2018/6/6 10:31
 */
public enum OmZipCode implements BaseEnum {

    /** 邮编： */
    Z100036("100036", ""),

    /** 邮编： */
    Z100040("100040", ""),

    /** 邮编： */
    Z100041("100041", ""),

    /** 邮编： */
    Z100043("100043", ""),

    /** 邮编： */
    Z100049("100049", "");

    private final String value;

    private final String name;

    OmZipCode(final String value, final String name) {
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
