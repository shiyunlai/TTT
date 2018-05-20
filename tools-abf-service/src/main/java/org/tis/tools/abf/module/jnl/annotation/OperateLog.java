package org.tis.tools.abf.module.jnl.annotation;

import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.abf.module.jnl.entity.enums.ReturnType;

import java.lang.annotation.*;

/**
 * 操作日志注解
 * 对数据库的增删改一般要记录操作日志，添加"OperateLog"注解可以持久化保存操作的对象等信息
 *
 * 该注解通过对方法返回值的拦截处理，实现操作记录的获取和存储
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog {

 /**
     * 操作类型，比如维护类方法有
     * 增:add,删:delete,改:update,查:query(默认为查)
     * 枚举值{@link OperateType}
     *
     * @return
     */
    OperateType type() default OperateType.QUERY;

    /**
     * 记录操作描述
     * 如： 新增菜单、 修改机构、 删除业务字典
     *
     * @return
     */
    String desc() default "";

}
