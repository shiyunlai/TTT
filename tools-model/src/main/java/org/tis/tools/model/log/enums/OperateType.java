package org.tis.tools.model.log.enums;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/5/15
 **/
public enum OperateType {

    SELECT("s", "查询"),
    INSERT("i", "新增"),
    UPDATE("u", "修改"),
    DELETE("d", "删除");

    private final String value;

    private final String name;

    OperateType(final String value, final String name) {
        this.value = value;
        this.name = name;
    }
}
