package org.tis.tools.abf.module.ac.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * 功能类型的枚举类
 * 业务菜单:DICT_AC_FUNCTYPE
 * @author  chenchao
 * Created on 2018/5/11 16:46
 */
public enum FuncType implements BaseEnum {

    /** 功能类型: 功能*/
    FUNCTION("F","功能"),

    /** 功能类型: 行为*/
    BEHAVE("B","行为");

    private final String value;

    private final String name;

    FuncType(String value,String name){
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
