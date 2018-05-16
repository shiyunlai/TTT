package org.tis.tools.abf.module.jnl.aop;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.enums.SqlMethod;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.tis.tools.abf.module.jnl.core.LogDataThreadLocal;
import org.tis.tools.abf.module.jnl.entity.enums.DataOperateType;
import org.tis.tools.abf.module.jnl.entity.vo.LogDataDetail;
import org.tis.tools.abf.module.jnl.util.DataUtils;
import org.tis.tools.abf.module.jnl.util.DiffUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/5/11
 **/
@Aspect
@Component
public class DataLogHandler {

    // TODO 目录调整后可能需要更改表达式
    @Pointcut("execution(public * org.tis.tools.abf.module.*.dao.*.insert*(..)) " +
            "&& !execution(public * org.tis.tools.abf.module.*.dao.jnl.insert*(..))" )
    public void insert() {
    }

    @Pointcut("execution(public * org.tis.tools.abf.module.*.dao.*.update*(..))" +
            "&& !execution(public * org.tis.tools.abf.module.*.dao.jnl.update*(..))" )
    public void update() {
    }

    @Pointcut("execution(public * org.tis.tools.abf.module.*.dao.*.delete*(..))" +
            "&& !execution(public * org.tis.tools.abf.module.*.dao.jnl.delete*(..))")
    public void delete() {
    }

    /**
     * 处理新增Mapper接口
     * @param pjp
     * @return
     * @throws Throwable
     */
    @AfterReturning(pointcut = "insert()", returning = "result", argNames = "pjp,result")
    public void insert(JoinPoint pjp, Integer result) throws Throwable {
        if (result > 0) {
            // 获取方法名
            String method = pjp.getSignature().getName();
            // insert 和 insertAllColumn 方法
            if (StringUtils.equals(method, SqlMethod.INSERT_ONE.getMethod()) ||
                    StringUtils.equals(method, SqlMethod.INSERT_ONE_ALL_COLUMN.getMethod())) {
                LogDataDetail data = new LogDataDetail();
                Object obj = pjp.getArgs()[0];
                data.setOperateType(DataOperateType.INSERT);
                collectDataInfo(data, obj);
                addDataLog(data);
            }
        }
    }

    /**
     * 处理删除Mapper接口
     * @param pjp
     * @throws Throwable
     */
    @Around("delete()")
    public Object delete(ProceedingJoinPoint pjp) throws Throwable {
        Object returnObj = null;
        // 获取方法名
        String method = pjp.getSignature().getName();
        // deleteById 方法
        if (StringUtils.equals(method, SqlMethod.DELETE_BY_ID.getMethod())) {
            // 执行处理逻辑
            returnObj = pjp.proceed();
            if (((Integer) returnObj) > 0) {
                if (StringUtils.equals(method, SqlMethod.DELETE_BY_ID.getMethod())) {
                    LogDataDetail data = new LogDataDetail();
                    data.setOperateType(DataOperateType.DELETE);
                    data.setDataGuid((String) pjp.getArgs()[0]);
                    Type type = pjp.getTarget().getClass().getGenericInterfaces()[0];
                    String dataClassName = ((ParameterizedType) type).getActualTypeArguments()[0].getTypeName();
                    data.setDataClass(dataClassName);
                    data.setDataName(DataUtils.getEntityName(Class.forName(dataClassName)));
                    addDataLog(data);
                }
            }
        }
        // deleteBatchIds 方法-【List<T> selectBatchIds(@Param("coll") Collection<? extends Serializable> idList)】
        // 和deleteByMap 方法-【List<T> selectByMap(@Param("cm") Map<String, Object> columnMap)】
        if (StringUtils.equals(method, SqlMethod.DELETE_BATCH_BY_IDS.getMethod()) ||
                StringUtils.equals(method, SqlMethod.DELETE_BY_MAP.getMethod())) {
            // 获取删除前数据
            List oldObjs;
            if (StringUtils.equals(method, SqlMethod.DELETE_BATCH_BY_IDS.getMethod())) {
                List idList = (List) pjp.getArgs()[0];
                Method selectMethod = pjp.getTarget().getClass()
                        .getDeclaredMethod(SqlMethod.SELECT_BATCH_BY_IDS.getMethod(), Collection.class);
                oldObjs = (List) selectMethod.invoke(pjp.getTarget(), idList);
            } else {
                Map columnMap = (Map) pjp.getArgs()[0];
                Method selectMethod = pjp.getTarget().getClass()
                        .getDeclaredMethod(SqlMethod.SELECT_BY_MAP.getMethod(), Map.class);
                oldObjs = (List) selectMethod.invoke(pjp.getTarget(), columnMap);
            }
            // 执行处理逻辑
            returnObj = pjp.proceed();
            if (((Integer) returnObj) > 0) {
                for (Object oldObj : oldObjs) {
                    LogDataDetail data = new LogDataDetail();
                    data.setOperateType(DataOperateType.DELETE);
                    collectDataInfo(data, oldObj);
                    addDataLog(data);
                }
            }
        }
        return returnObj;
    }

    /**
     * 处理修改Mapper接口
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("update()")
    public Object update(ProceedingJoinPoint pjp) throws Throwable {
        Object returnObj = null;
        // 获取方法名
        String method = pjp.getSignature().getName();
        // updateById 和 updateAllColumnById 方法
        if (StringUtils.equals(method, SqlMethod.UPDATE_BY_ID.getMethod())
                || StringUtils.equals(method, SqlMethod.UPDATE_ALL_COLUMN_BY_ID.getMethod())) {
            LogDataDetail data = new LogDataDetail();
            data.setOperateType(DataOperateType.UPDATE);
            Object obj = pjp.getArgs()[0];
            collectDataInfo(data, obj);
            // 获取修改前数据
            Method selectMethod = pjp.getTarget().getClass()
                    .getDeclaredMethod(SqlMethod.SELECT_BY_ID.getMethod(), Serializable.class);
            Object oldObj = selectMethod.invoke(pjp.getTarget(), data.getInstance().getDataGuid());
            // 执行处理逻辑
            returnObj = pjp.proceed();
            // 处理差异
            if (((Integer) returnObj) > 0) {
                if (StringUtils.equals(method, SqlMethod.UPDATE_BY_ID.getMethod())) {
                    data.setChanges(DiffUtils.getChangeItems(oldObj, obj, true));
                } else {
                    data.setChanges(DiffUtils.getChangeItems(oldObj, obj, false));
                }
            }
            addDataLog(data);
        }

        // update 方法-【Integer update(@Param("et") T entity, @Param("ew") Wrapper<T> wrapper)】
        if (StringUtils.equals(method, SqlMethod.UPDATE.getMethod())) {
            Wrapper wrapper = (Wrapper) pjp.getArgs()[1];
            Object obj = pjp.getArgs()[0];
            Method selectMethod = pjp.getTarget().getClass()
                    .getDeclaredMethod(SqlMethod.SELECT_LIST.getMethod(), Wrapper.class);
            // 获取修改前数据
            List oldObjs = ((List) selectMethod.invoke(pjp.getTarget(), wrapper));
            // 执行处理逻辑
            returnObj = pjp.proceed();
            // 处理差异
            if (((Integer) returnObj) > 0) {
                for (Object oldObj : oldObjs) {
                    LogDataDetail data = new LogDataDetail();
                    data.setOperateType(DataOperateType.UPDATE);
                    collectDataInfo(data, oldObj);
                    data.setChanges(DiffUtils.getChangeItems(oldObj, obj, true));
                    addDataLog(data);
                }
            }
        }
        return returnObj;
    }

    private void collectDataInfo(LogDataDetail data, Object obj) {
        data.setDataClass(obj.getClass().getName())
                .setDataString(JSON.toJSONString(obj))
                .setDataName(DataUtils.getEntityName(obj.getClass()))
                .setDataGuid(DataUtils.getEntityId(obj));
    }

    private void addDataLog(LogDataDetail data) {
        List<LogDataDetail> list = LogDataThreadLocal.getLogDataLocal();
        if (CollectionUtils.isEmpty(list)) {
            List<LogDataDetail> dataDetails = new ArrayList<>();
            dataDetails.add(data);
            LogDataThreadLocal.setLogDataLocal(dataDetails);
        } else {
            list.add(data);
        }
    }


}
