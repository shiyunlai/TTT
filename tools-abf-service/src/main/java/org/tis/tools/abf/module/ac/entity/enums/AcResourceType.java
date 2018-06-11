package org.tis.tools.abf.module.ac.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * 资源类型
 * Created by chenchao
 * Created on 2018/6/8 15:42
 */
public enum AcResourceType implements BaseEnum {

    /** 资源类型:功能 */
    FUNCTION("function","功能"),

    /** 资源类型:角色 */
    ROLE("role","角色"),

    /** 资源类型:岗位 */
    POSITION("position","岗位"),

    /** 资源类型:工作组 */
    WORKGROUP("workgroup","工作组"),

    /** 资源类型:机构 */
    ORGANIZATION("organization","机构");


    private final String value;

    private final String name;

    AcResourceType(final String value,final String name){
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
