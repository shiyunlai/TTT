package org.tis.tools.abf.module.sys.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

public enum SeqnoReset implements BaseEnum {

    EVER("E","不重置"),

    DAY("D","按天重置"),

    WEEK("W","按周重置"),

    CUSTOM("C","自定义重置周期");

    private final String value;

    private final String name;


    SeqnoReset(final String value, final String name) {
        this.value = value;
        this.name = name;
    }


    @Override
    public Serializable getValue() {
        return this.value;
    }

    @Override
    public Serializable deserialze() { return value; }

    public  String getDesc(){ return this.name; }

    @Override
    public String toString() { return name; }
}
