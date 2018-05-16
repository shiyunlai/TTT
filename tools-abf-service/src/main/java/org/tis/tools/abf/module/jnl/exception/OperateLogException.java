package org.tis.tools.abf.module.jnl.exception;

import org.tis.tools.core.exception.ToolsRuntimeException;

/**
 * description: 操作日志异常类
 *
 * @author zhaoch
 * @date 2018/5/10
 **/
public class OperateLogException extends ToolsRuntimeException {

    private static final long serialVersionUID = 1L;

    public OperateLogException(){ };

    public OperateLogException(String code) {
        super(code);
    }

    public OperateLogException(String code, Throwable cause) {
        super(code, cause);
    }

    public OperateLogException(String code, Object[] placeholders) {
        super(code,placeholders) ;
    }

    public OperateLogException(String code, Object[] params, String message) {
        super(code,params,message) ;
    }

    public OperateLogException(String code, String message) {
        super(code,message) ;
    }
    
}
