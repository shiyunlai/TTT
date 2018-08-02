package org.tis.tools.abf.module.jnl.aop;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.tis.tools.abf.module.jnl.core.LogDataThreadLocal;
import org.tis.tools.abf.module.jnl.core.OperateLogBuilder;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateResult;
import org.tis.tools.abf.module.jnl.entity.vo.LogDataDetail;
import org.tis.tools.abf.module.jnl.entity.vo.LogOperateDetail;
import org.tis.tools.abf.module.jnl.service.ILogAbfOperateService;
import org.tis.tools.core.exception.ToolsRuntimeException;
import org.tis.tools.core.utils.BasicUtil;
import org.tis.tools.model.common.ResultVO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * describe: 操作日志处理类
 *
 * @author zhaoch
 * @date 2018/4/23
 **/
@Component
@Aspect
public class OperateLogHandler {

    @Autowired
    private ILogAbfOperateService logOperatorRService;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 异步操作记录日志的线程池
     */
    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10,
            new ThreadFactoryBuilder().setNameFormat("abf-log-pool-%d").build());

    /**
     * 需要记录日志的切入点
     */
    @Pointcut("execution(public org.tis.tools.model.common.ResultVO org.tis.tools.abf.module..*Controller.*(..))")
    public void logPoint() {}


    @Around("logPoint()")
    public Object handle(ProceedingJoinPoint point)  throws Throwable {
        Object result = null;
        //执行业务
        boolean flag = true;
        Exception exception = null;
        try {
            result = point.proceed();
        } catch (Exception e) {
            exception = e;
            flag = false;
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取请求路径
        String uri = request.getServletPath();
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 0) {
            uri = uri + pathInfo;
        }
        // 获取方法参数
        String paramString = getMethodInfo(point);
        logger.info(" [请求] Request URL:{}; Request Method:{}; Request Body:{}", BasicUtil.wrap(uri,
                request.getMethod(), paramString)) ;
        // 获取操作日志注解
        Signature sig = point.getSignature();
        MethodSignature msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());

        OperateLog log = currentMethod.getAnnotation(OperateLog.class);
        if (log != null) {
//          HttpSession session = request.getSession();
//          Session session = SecurityUtils.getSubject().getSession();
//          AcOperator acOperator = (AcOperator) session.getAttribute("userInfo");
            OperateLogBuilder logBuilder = new OperateLogBuilder();
            logBuilder.start()
                    // FIXME 从哪设置该值
                    .setOperateFrom("ABF")
                    .setUserId("开发用户TODO")
                    .setOperatorName("开发用户TODO")
                    .setServiceId(request.getRemoteHost() + "(" + request.getRemoteAddr() + ")")
//                  .setUserId(StringUtils.equals(jnl.operateType(),"login") ? "" : session.getAttribute("userId").toString())
//                  .setOperatorName(StringUtils.equals(jnl.operateType(), "login") ? "" : acOperator.getOperatorName())
                    .setOperateType(log.type())
                    .setOperateDesc(log.desc())
                    .setRestfulUrl(uri);
//            LogThreadLocal.setLogBuilderLocal(logBuilder);
            try {
                if (flag) {
                    handleSuccessReturn(logBuilder, log, point, (ResultVO) result);
                } else {
                    handleErrorReturn(logBuilder, exception);
                }
            } catch (Exception e) {
                logger.error("处理日志信息发生异常：", e);
            }
        }
        if (flag) {
            String objStr = JSON.toJSONString(((ResultVO)result).getResult());
            logger.info(" [响应] Request URL:{}; Request Method:{}; Response Body:{}", BasicUtil.wrap(uri,
                    request.getMethod(), objStr)) ;
            return result;
        } else {
            logger.error(" [响应] Request URL:{}; Request Method:{}; Response Body:{}", BasicUtil.wrap(uri,
                    request.getMethod(), getStackTraceString(exception))) ;
            throw exception;
        }
    }

    /**
     * 保存操作日志
     */
    private void saveOperateLog(LogOperateDetail log) {
        try {
            // 日志记录操作延时
            int operateDelayTime = 3000;
            executor.schedule(businessLog(log)
                    , operateDelayTime, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            logger.error("保存日志出错", e);
        }
    }

    /**
     * 异步线程保存日志
     * @param log
     * @return
     */
    private TimerTask businessLog(LogOperateDetail log) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    logOperatorRService.insertOperatorLog(log);
                } catch (Exception e) {
                    logger.error("创建业务日志异常!", e);
                }
            }
        };
    }

    /**
     * 获取方法参数
     * @param point
     * @return
     */
    private String getMethodInfo(JoinPoint point) {
        String className = point.getSignature().getDeclaringType().getSimpleName();
        String methodName = point.getSignature().getName();
        String[] parameterNames = ((MethodSignature) point.getSignature()).getParameterNames();
        StringBuilder sb = null;
        if (Objects.nonNull(parameterNames)) {
            sb = new StringBuilder();
            for (int i = 0; i < parameterNames.length; i++) {
                String value = point.getArgs()[i] != null ? point.getArgs()[i].toString() : "null";
                sb.append(parameterNames[i]).append(":").append(value).append("; ");
            }
        }
        sb = sb == null ? new StringBuilder() : sb;
        return String.format("{method:[%s#%s] | args:[%s]}", className, methodName, sb.toString());
    }

    /**
     * 处理成功请求信息
     * @param logAnt
     * @param point
     * @param ret
     */
    private void handleSuccessReturn(OperateLogBuilder logBuilder, OperateLog logAnt, JoinPoint point, ResultVO ret) {
        LogOperateDetail log = logBuilder.getLog();
        log.setOperateResult(OperateResult.SUCCESS);
        // 添加数据变化项到操作日志
        List<LogDataDetail> logDataList = LogDataThreadLocal.getLogDataLocal();
        if (!CollectionUtils.isEmpty(logDataList)) {
            logBuilder.getLog().setLogDatas(logDataList);
        }
        saveOperateLog(logBuilder.getLog());
    }

    /**
     * 处理失败请求信息，返回错误堆栈
     * @param e
     * @return
     * @throws IOException
     */
    private void handleErrorReturn(OperateLogBuilder logBuilder, Exception e) throws IOException {
//        OperateLogBuilder logBuilder = LogThreadLocal.getLogBuilderLocal();
        if(e instanceof ToolsRuntimeException) {
            logBuilder.getLog().setOperateResult(OperateResult.FAILURE);
        } else {
            logBuilder.getLog().setOperateResult(OperateResult.ERROR);
        }
        String message = getStackTraceString(e);
        // 数据库设置字段长度为4000，一些异常堆栈过长，截取4000长度保存
        logBuilder.getLog().setStackTrace(message.length() > 4000 ? message.substring(0, 3999) : message);
        saveOperateLog(logBuilder.getLog());
    }

    /**
     * 获取异常堆栈字符
     * @param e
     * @return
     */
    private String getStackTraceString(Exception e) throws IOException {
        // 获取堆栈String
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String message = sw.toString();
        pw.close();
        sw.close();
        return message;
    }

}
