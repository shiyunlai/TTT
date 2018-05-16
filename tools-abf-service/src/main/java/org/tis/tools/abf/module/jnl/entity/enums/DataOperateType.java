package org.tis.tools.abf.module.jnl.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/5/14
 **/
public enum DataOperateType implements BaseEnum {

    SELECT("s", "查询"),
    INSERT("i", "新增"),
    UPDATE("u", "修改"),
    DELETE("d", "删除");

    private final String value;

    private final String name;

    DataOperateType(final String value, final String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public Serializable deserialze() {
        return name;
    }

    @Override
    public Serializable getValue() {
        return value;
    }
}
