package org.tis.tools.abf.module.jnl.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tis.tools.abf.module.jnl.entity.LogAbfChange;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DiffUtils {

    private static final Logger logger = LoggerFactory.getLogger(DiffUtils.class);

    /**
     * 获取更新操作的change item
     *
     * @param oldObj
     * @param newObj
     * @param ignoreNull 是否忽略null
     * @return
     */
    public static List<LogAbfChange> getChangeItems(Object oldObj, Object newObj, boolean ignoreNull) {
        Class cl = oldObj.getClass();
        List<LogAbfChange> changeItems = new ArrayList<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(cl, Object.class);
            for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
                String field = propertyDescriptor.getName();
                //获取字段旧值
                String oldProp = getValue(PropertyUtils.getProperty(oldObj, field));
                //获取字段新值
                String newProp = getValue(PropertyUtils.getProperty(newObj, field));
                //对比新旧值
                if (!oldProp.equals(newProp)) {
                    // 忽略空值
                    if (!(ignoreNull && StringUtils.isBlank(newProp))) {
                        LogAbfChange changeItem = new LogAbfChange();
                        changeItem.setPhysicalName(field);
                        String cnName = (String) cl.getField("NAME_" + camel2Underline(field).toUpperCase()).get(null);
                        changeItem.setLogicName(StringUtils.isEmpty(cnName) ? field : cnName);
                        changeItem.setNewValue(newProp);
                        changeItem.setOldValue(oldProp);
                        changeItems.add(changeItem);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("There is error when convert change item", e);
        }
        return changeItems;
    }

    /**
     * 不同类型转字符串的处理
     *
     * @param obj
     * @return
     */
    public static String getValue(Object obj) {
        if (obj != null) {
            if (obj instanceof Date) {
                return formatDateW3C((Date) obj);
            } else {
                return obj.toString();
            }
        } else {
            return "";
        }
    }


    /**
     * 将date类型转为字符串形式
     *
     * @param date
     * @return
     */
    public static String formatDateW3C(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String text = df.format(date);
        return text.substring(0, 22) + ":" + text.substring(22);
    }

    /**
     * 获取bean的fieldname和value
     * 只获取简单类型，不获取复杂类型，包括集合
     *
     * @param bean
     * @return
     */
    public static Map<String, String> getBeanSimpleFieldValueMap(Object bean, boolean filterNull) {
        Map<String, String> map = new HashMap<>(16);
        if (bean == null) {
            return map;
        }
        Class<?> clazz = bean.getClass();
        try {
            //不获取父类的字段
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Class<?> fieldType = field.getType();
                String name = field.getName();
                Method method = clazz.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
                Object value = method.invoke(bean);
                if (filterNull && value == null) {
                    continue;
                }
                if (isBaseDataType(fieldType)) {
                    String strValue = getFieldStringValue(fieldType, value);
                    map.put(name, strValue);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return map;
    }

    /**
     * 自定义不同类型的string值
     *
     * @param type
     * @return
     */
    public static String getFieldStringValue(Class type, Object value) {
        if (type.equals(Date.class)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formatter.format((Date) value);
        }
        return value.toString();
    }

    /**
     * 判断一个类是否为基本数据类型或包装类，或日期。
     *
     * @param clazz 要判断的类。
     * @return true 表示为基本数据类型。
     */
    public static boolean isBaseDataType(Class clazz) throws Exception {
        return (
                clazz.equals(String.class)
                        || clazz.equals(Integer.class)
                        || clazz.equals(Byte.class)
                        || clazz.equals(Long.class)
                        || clazz.equals(Double.class)
                        || clazz.equals(Float.class)
                        || clazz.equals(Character.class)
                        || clazz.equals(Short.class)
                        || clazz.equals(BigDecimal.class)
                        || clazz.equals(BigInteger.class)
                        || clazz.equals(Boolean.class)
                        || clazz.equals(Date.class)
                        || clazz.isPrimitive()
        );
    }

    /**
     * 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。</br>
     * 例如：HelloWorld->HELLO_WORLD
     *
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线大写方式命名的字符串
     */
    public static String camel2Underline(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }


}
