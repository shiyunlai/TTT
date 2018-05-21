package org.tis.tools.abf.module.jnl.entity.enums;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/5/15
 **/
public enum DataMethod {

    /**
     * 插入
     */
    INSERT("insert"),
    INSERT_ALL_COLUMN("insertAllColumn"),

    /**
     * 删除
     */
    DELETE_BY_ID("deleteById"),
    DELETE_BY_MAP("deleteByMap"),
    DELETE("delete"),
    DELETE_BATCH_BY_IDS("deleteBatchIds"),

    /**
     * 修改
     */
    UPDATE_BY_ID("updateById"),
    UPDATE_ALL_COLUMN_BY_ID("updateAllColumnById"),
    UPDATE("update");

    private final String value;

    DataMethod(final String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }


}
