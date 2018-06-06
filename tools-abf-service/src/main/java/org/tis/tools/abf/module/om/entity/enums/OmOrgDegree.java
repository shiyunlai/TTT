package org.tis.tools.abf.module.om.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * 机构登记
 * Created by chenchao
 * Created on 2018/6/5 11:55
 */
public enum OmOrgDegree implements BaseEnum {

    /** 机构登记:总行 */
    HEADQUARTERS("BS", "总行"),

    /** 机构登记:分行 */
    BRANCH("YF", "分行"),

    /** 机构登记:海外 */
    OVERSEAS("HW", "海外"),

    /** 机构登记:区域分行 */
    REGIONALBRANCH("QY", "区域分行"),

    /** 机构登记:网点 */
    NETWORK("CN", "网点");


    private final String value;

    private final String name;


    OmOrgDegree(final String value, final String name){
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
