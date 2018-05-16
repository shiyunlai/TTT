package org.tis.tools.abf.module.jnl.core;

import org.springframework.util.CollectionUtils;
import org.tis.tools.abf.module.jnl.entity.vo.LogDataDetail;

import java.util.Collections;
import java.util.List;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/5/15
 **/
public class LogDataThreadLocal {

    private static ThreadLocal<List<LogDataDetail>> LOG_DATA_LOCAL = new ThreadLocal<>();

    /**
     * GET
     * @return
     */
    public static List<LogDataDetail> getLogDataLocal(){
        return LOG_DATA_LOCAL.get();
    }

    /**
     * SET
     * @param logDataLocal
     */
    public static void setLogDataLocal(List<LogDataDetail> logDataLocal) {
        LOG_DATA_LOCAL.set(logDataLocal);
    }
}
