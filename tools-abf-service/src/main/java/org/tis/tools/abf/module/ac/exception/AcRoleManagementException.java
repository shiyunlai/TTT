package org.tis.tools.abf.module.ac.exception;

import org.tis.tools.core.exception.ToolsRuntimeException;
/**
 *
 * 角色管理服务异常对象
 *
 * @author wengfanglei
 *
 */
public class AcRoleManagementException extends ToolsRuntimeException {

    private static final long serialVersionUID = 1L;

    public AcRoleManagementException (){}


    public AcRoleManagementException (String code){
        super(code);
    }


    public AcRoleManagementException (String code, Object[] placeholders){

        super(code, placeholders);
    }


    public AcRoleManagementException (String code, Object[] params, String message){

        super(code,params,message) ;
    }


    public AcRoleManagementException (String code, String message){
        super(code,message) ;
    }


}
