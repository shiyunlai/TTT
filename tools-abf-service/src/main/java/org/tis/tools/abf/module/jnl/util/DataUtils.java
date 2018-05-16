package org.tis.tools.abf.module.jnl.util;

import com.baomidou.mybatisplus.annotations.TableId;
import org.apache.commons.lang3.StringUtils;
import org.tis.tools.abf.module.jnl.exception.OperateLogException;
import org.tis.tools.abf.module.jnl.exception.OperateLogExceptionCodes;

import java.lang.reflect.Field;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/5/16
 **/
public class DataUtils {

    /**
     * 实体类默认生成一个名为 NAME 的静态常量，值为实体逻辑名称
     *
     * 如Employee类有：
     * public static fianl String NAME = "员工"
     */
    private static final String DATA_NAME = "NAME";

    /**
     * 获取实体对象的ID，反射获取带有{@link TableId}注解的属性值
     * @param object
     * @return
     * @throws IllegalAccessException
     */
    public static String getEntityId(Object object) {
        String id = null;
        TableId annotation = null;
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field f : declaredFields) {
            annotation = f.getAnnotation(TableId.class);
            if (annotation != null) {
                f.setAccessible(true);
                try {
                    id = (String) f.get(object);
                    break;
                } catch (IllegalAccessException ignored) {

                }
            }
        }
        if (annotation == null) {
            throw new OperateLogException(OperateLogExceptionCodes.NOT_FOUND_ENTITY_ID_ANNOTATION);
        } else if (StringUtils.isBlank(id)) {
            throw new OperateLogException(OperateLogExceptionCodes.ENTITY_ID_IS_BLANK);
        }
        return id;
    }

    /**
     * 获取实体类的逻辑名称，反射获指定属性名称{@link DataUtils#DATA_NAME}的属性值
     * @param clazz
     * @return
     */
    public static String getEntityName(Class clazz) {
        try {
            return (String) clazz.getField(DATA_NAME).get(null);
        } catch (Exception e) {
            throw new OperateLogException(OperateLogExceptionCodes.NOT_FOUND_ENTITY_NAME);
        }
    }


}
