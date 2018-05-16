package org.tis.tools.abf.module.jnl.exception;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/5/10
 **/
public class OperateLogExceptionCodes {
    /**
     * 异常码前缀（分配给core模块）： log
     */
    private static final String R_EX_PREFIX = "LOG";

    /**
     * 返回结果为null
     */
    public static final String RETURN_RESULT_IS_NULL = R_EX_CODE("0001");

    /**
     * 返回类型不正确
     */
    public static final String RETURN_TYPE_INCORRECT = R_EX_CODE("0002");

    /**
     * 找不到实体名称
     */
    public static final String NOT_FOUND_ENTITY_NAME = R_EX_CODE("0003");

    /**
     * 找不到实体ID注解
     */
    public static final String NOT_FOUND_ENTITY_ID_ANNOTATION = R_EX_CODE("0004");

    /**
     * 实体ID为空
     */
    public static final String ENTITY_ID_IS_BLANK = R_EX_CODE("0005");

    /**
     * 以烤串方式拼接异常码
     *
     * @param code
     *            业务域范围内的异常编码
     * @return
     */
    private static String R_EX_CODE(String code) {
        return R_EX_PREFIX + "-" + code;
    }
}
