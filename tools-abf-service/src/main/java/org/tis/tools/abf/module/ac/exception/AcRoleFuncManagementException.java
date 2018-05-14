package org.tis.tools.abf.module.ac.exception;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.tis.tools.core.exception.ToolsRuntimeException;

public class AcRoleFuncManagementException extends ToolsRuntimeException {

    private static final long serialVersionUID = 1L;

    public AcRoleFuncManagementException(){};

    public AcRoleFuncManagementException(String code){
        super(code);
    }

    public AcRoleFuncManagementException(String code, Object[] placeholders){

        super(code, placeholders);
    }

    public AcRoleFuncManagementException(String code, Object[] params, String message){

        super(code,params,message);
    }

    public AcRoleFuncManagementException(String code, String message){

        super(code,message);
    }
}
