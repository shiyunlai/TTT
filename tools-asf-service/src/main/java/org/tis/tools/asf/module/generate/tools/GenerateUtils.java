package org.tis.tools.asf.module.generate.tools;

import org.apache.commons.lang3.StringUtils;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/10
 **/
public class GenerateUtils {


    /**
     * 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。</br>
     * 例如：HelloWorld->HELLO_WORLD
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

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
     * 例如：HELLO_WORLD->HelloWorld
     * @param name 转换前的下划线大写方式命名的字符串
     * @param firstUpper 首字母是否小写
     * @return 转换后的驼峰式命名的字符串
     */
    public static String underline2Camel(String name, boolean firstUpper) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.toLowerCase();
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split("_");
        for (String camel :  camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0 && firstUpper) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String firstUpperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 首字母小写
     * @param str
     * @return
     */
    public static String firstLowerCase(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 包裹引号
     * 	例： APP123123  -> "APP123123"
     * @param val
     * @return
     */
    public static String surroundQuote(String val) {
        if (StringUtils.isNotBlank(val)) {
            return "\"" + val + "\"";
        }
        return "";
    }
}