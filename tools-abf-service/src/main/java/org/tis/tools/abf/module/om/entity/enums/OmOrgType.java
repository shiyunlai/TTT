package org.tis.tools.abf.module.om.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * 机构类型
 * 见业务字典： DICT_OM_ORGTYPE  如：总公司/总部部门/分公司/分公司部门.....
 * Created by chenchao
 * Created on 2018/6/5 10:42
 */
public enum OmOrgType implements BaseEnum {

    /** 机构类型:总公司 */
    OFFICE("10", "总公司"),

    /** 机构类型:总部部门 */
    OFFICEDEPARTMENT("11", "总部部门"),

    /** 机构类型:分公司 */
    BRANCHOFFICE("20", "分公司"),

    /** 机构类型:分公司部门 */
    BRANCHOFFICEDEPARTMENT("21", "分公司部门"),

    /** 机构类型:营业网点 */
    BUSINESSNETWORK("90", "营业网点");


    private final String value;

    private final String name;


    OmOrgType(final String value, final String name){
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
