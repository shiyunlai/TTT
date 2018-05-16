package org.tis.tools.abf.module.jnl.entity.vo;

import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.abf.module.jnl.entity.enums.OperateResult;
import org.tis.tools.abf.module.jnl.entity.LogAbfOperate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * describe: 操作日志VO类
 *
 * @author zhaoch
 * @date 2018/5/14
 **/
public class LogOperateDetail implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    private LogAbfOperate log = new LogAbfOperate();

    private List<LogDataDetail> logDatas = new ArrayList<>();

    public void setLogDatas(List<LogDataDetail> logDatas) {
        this.logDatas = logDatas;
    }

    public void setLog(LogAbfOperate log) {
        this.log = log;
    }

    public LogOperateDetail(){}

    public LogDataDetail addLogData() {
        LogDataDetail obj = new LogDataDetail();
        this.logDatas.add(obj);
        return obj;
    }

    public LogDataDetail addLogData(LogDataDetail obj) {
        this.logDatas.add(obj);
        return obj;
    }

    public LogDataDetail getLogData(int index) {
        return this.logDatas.get(index);
    }

    public List<LogDataDetail> getAllLogData() {
        return this.logDatas;
    }


    public LogAbfOperate getInstance() {
        return log;
    }
    /**
     * Set the 操作渠道.
     */
    public LogOperateDetail setOperateFrom(String operateFrom) {
        this.log.setOperateFrom(operateFrom == null ? null : operateFrom.trim());
        return this;
    }

    /**
     * Set the 操作类型.
     *
     * @param operateType
     *            操作类型
     */
    public LogOperateDetail setOperateType(OperateType operateType) {
        this.log.setOperateType(operateType);
        return this;
    }


    /**
     * Set the 操作时间.
     *
     * @param operateTime
     *            操作时间
     */
    public LogOperateDetail setOperateTime(Date operateTime) {
        this.log.setOperateTime(operateTime);
        return this;
    }


    /**
     * Set the 操作描述.
     *
     * @param operateDesc
     *            操作描述
     */
    public LogOperateDetail setOperateDesc(String operateDesc) {
        this.log.setOperateDesc(operateDesc == null ? null : operateDesc.trim());
        return this;
    }


    /**
     * Set the 操作结果.
     *
     * @param operateResult
     *            操作结果
     */
    public LogOperateDetail setOperateResult(OperateResult operateResult) {
        this.log.setOperateResult(operateResult);
        return this;
    }


    /**
     * Set the 操作员姓名.
     *
     * @param operatorName
     *            操作员姓名
     */
    public LogOperateDetail setOperatorName(String operatorName) {
        this.log.setOperatorName(operatorName == null ? null : operatorName.trim());
        return this;
    }


    /**
     * Set the 操作员.
     *
     * @param userId
     *            操作员
     */
    public LogOperateDetail setUserId(String userId) {
        this.log.setUserId(userId == null ? null : userId.trim());
        return this;
    }


    /**
     * Set the 应用代码.
     *
     * @param appCode
     *            应用代码
     */
    public LogOperateDetail setAppCode(String appCode) {
        this.log.setAppCode(appCode == null ? null : appCode.trim());
        return this;
    }

    /**
     * Set the 应用名称.
     *
     * @param appName
     *            应用名称
     */
    public LogOperateDetail setAppName(String appName) {
        this.log.setAppName(appName == null ? null : appName.trim());
        return this;
    }

    /**
     *
     * @param serviceId
     * @return
     */
    public LogOperateDetail setServiceId(String serviceId) {
        this.log.setServiceId(serviceId == null ? null : serviceId.trim());
        return this;
    }

    /**
     * Set the 服务地址.
     *
     * @param restfulUrl
     *            服务地址
     */
    public LogOperateDetail setRestfulUrl(String restfulUrl) {
        this.log.setRestfulUrl(restfulUrl == null ? null : restfulUrl.trim());
        return this;
    }

    /**
     * Set the 异常堆栈.
     *
     * @param stackTrace
     *            异常堆栈
     */
    public LogOperateDetail setStackTrace(String stackTrace) {
        this.log.setStackTrace(stackTrace == null ? null : stackTrace.trim());
        return this;
    }

    /**
     * Set the 处理描述.
     *
     * @param processDesc
     *            处理描述
     */
    public LogOperateDetail setProcessDesc(String processDesc) {
        this.log.setProcessDesc(processDesc == null ? null : processDesc.trim());
        return this;
    }

}
