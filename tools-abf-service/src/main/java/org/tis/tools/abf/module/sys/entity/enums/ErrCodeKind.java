package org.tis.tools.abf.module.sys.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * Created by chenchao
 * Created on 2018/6/1 13:42
 */
public enum ErrCodeKind implements BaseEnum {

    SYS("S","系统错误码"),

    TRANS("T","交易错误码");

    private final String value;

    private final String name;

    ErrCodeKind(final String value,final String name){
        this.name = name;
        this.value = value;
    }

    @Override
    public Serializable deserialze() { return value; }

    @Override
    public Serializable getValue() { return value; }

    @Override
    public String toString() { return name; }

    public  String getDesc(){ return this.name; }
}
