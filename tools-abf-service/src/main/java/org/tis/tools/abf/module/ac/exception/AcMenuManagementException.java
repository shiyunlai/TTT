package org.tis.tools.abf.module.ac.exception;

import org.tis.tools.core.exception.ToolsRuntimeException;

/**
 * Created by ministrator on 2018/5/8.
 */
public class AcMenuManagementException extends ToolsRuntimeException {
    private static final long serialVersionUID = 1L;

    public AcMenuManagementException() {super();}

    public AcMenuManagementException(String code) {
        super(code);
    }

    public AcMenuManagementException(String code, Object[] placeholders) {
        super(code,placeholders) ;
    }

    public AcMenuManagementException(String code, Object[] params, String message) {
        super(code,params,message) ;
    }

    public AcMenuManagementException(String code, String message) {
        super(code,message) ;
    }
}
