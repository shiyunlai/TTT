package org.tis.tools.abf.module.ac.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * 人员特殊权限授权标志
 * Created by chenchao
 * Created on 2018/6/11 9:20
 */
public enum AcAuthType implements BaseEnum {

    /** 人员特殊权限授权标志:特别开通 */
    PERMIT("1","特别开通"),

    /** 人员特殊权限授权标志:特别禁止 */
    FORBID("0","特别禁止");


    private final String value;

    private final String name;

    AcAuthType(final String value,final String name){
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
