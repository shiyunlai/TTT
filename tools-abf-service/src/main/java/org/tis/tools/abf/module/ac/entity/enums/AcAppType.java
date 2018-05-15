package org.tis.tools.abf.module.ac.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * 应用类型枚举类
 * Created by chenchao
 * Created on 2018/4/26 11:24
 */
public enum AcAppType implements BaseEnum {

    /** 应用类型:本地 */
    LOCAL("local","本地"),

    /** 应用类型:远程 */
    REMOTE("remote","远程");


    private final String value;

    private final String name;

    AcAppType(final String value,final String name){
        this.name = name;
        this.value = value;
    }

    @Override
    public String getValue() { return this.value; }

    @Override
    public Serializable deserialze() { return value; }

    public  String getDesc(){ return this.name; }

    @Override
    public String toString() { return name; }
}
