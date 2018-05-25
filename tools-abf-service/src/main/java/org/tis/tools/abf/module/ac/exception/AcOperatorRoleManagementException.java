package org.tis.tools.abf.module.ac.exception;

import org.tis.tools.core.exception.ToolsRuntimeException;

public class AcOperatorRoleManagementException extends ToolsRuntimeException {
    private static final long serialVersionUID = 1L;

    public AcOperatorRoleManagementException(){};

    public AcOperatorRoleManagementException(String code){
        super(code);
    }

    public AcOperatorRoleManagementException(String code, Object[] placeholders){
        super(code,placeholders);
    }

    public AcOperatorRoleManagementException(String code, Object[] params, String message){
        super(code,params,message);
    }

    public AcOperatorRoleManagementException(String code, String message){
        super(code,message);
    }
}
