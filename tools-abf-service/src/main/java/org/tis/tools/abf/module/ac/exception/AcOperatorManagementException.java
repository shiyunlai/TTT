package org.tis.tools.abf.module.ac.exception;

import org.tis.tools.core.exception.ToolsRuntimeException;

public class AcOperatorManagementException extends ToolsRuntimeException {

    private static final long serialVersionUID = 1L;

    public AcOperatorManagementException(){}

    public AcOperatorManagementException(String code){
        super(code);
    }

    public AcOperatorManagementException(String code, Object[] placeholders){
        super(code,placeholders);
    }

    public AcOperatorManagementException(String code, Object[] params, String message){
        super(code,params,message);
    }

    public AcOperatorManagementException(String code, String message){
        super(code,message);
    }
}
