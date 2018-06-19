package org.tis.tools.abf.module.om.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * describe: 工作组状态枚举类
 *
 * @author lijh
 * @date 2018/6/14
 **/
public enum OmGroupType implements BaseEnum {

    /** 工作组类型：普通工作组 */
    NORMAL("normal", "普通工作组"),

    /** 工作组类型：项目型 */
    PROJECT("project", "项目型"),

    /** 工作组类型：事务型 */
    AFFAIR("affair", "事务型");

    private final String value;

    private final String name;

    OmGroupType(final String value, final String name) {
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
