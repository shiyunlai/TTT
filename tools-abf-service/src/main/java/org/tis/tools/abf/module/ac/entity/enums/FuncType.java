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

    /** 功能类型: 页面流*/
    PAGEPROCESS("0","页面流"),

    /** 功能类型: 交易流*/
    TRADEPROCESS("1","交易流"),

    /** 功能类型: RESTFul服务*/
    RESTFUL("2","RESTFul服务"),

    /** 功能类型: 柜面交易*/
    TWSTX("3","柜面交易");

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
