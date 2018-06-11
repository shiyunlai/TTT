package org.tis.tools.abf.module.ac.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * 功能界面打开方式
 * Created by chenchao
 * Created on 2018/6/11 15:58
 */
public enum AcOpenMode implements BaseEnum {
    /** 功能界面打开方式: 主操作区*/
    MAINAREA("mainarea","主操作区"),

    /** 功能界面打开方式: 弹出框方式*/
    POPUP("popup","弹出框方式");

    private final String value;

    private final String name;

    AcOpenMode(String value,String name){
        this.value = value;
        this.name = name;
    }

    @Override
    public Serializable deserialze() { return value; }

    @Override
    public Serializable getValue() { return value; }

    public  String getDesc(){ return this.name; }

    @Override
    public String toString() { return name; }
}
