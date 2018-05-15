package org.tis.tools.abf.module.ac.exception;

import org.tis.tools.core.exception.ToolsRuntimeException;

/**
 * 应用管理服务异常
 * Created by chenchao
 * Created on 2018/5/3 16:34
 */
public class AcManagementException extends ToolsRuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public AcManagementException(){ }

    public AcManagementException(String code) {
        super(code);
    }

    public AcManagementException(String code, Object[] placeholders) {
        super(code,placeholders) ;
    }

    public AcManagementException(String code, Object[] params, String message) {
        super(code,params,message) ;
    }

    public AcManagementException(String code, String message) {
        super(code,message) ;
    }
}
