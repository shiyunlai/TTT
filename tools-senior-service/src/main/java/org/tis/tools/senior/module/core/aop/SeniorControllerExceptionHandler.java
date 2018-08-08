package org.tis.tools.senior.module.core.aop;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tis.tools.senior.module.developer.exception.DeveloperException;
import org.tis.tools.core.aop.BaseControllerExceptionHandler;
import org.tis.tools.model.common.ResultVO;
import org.tmatesoft.svn.core.SVNException;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author
 * @date 2018/04/18
 */
@RestControllerAdvice
public class SeniorControllerExceptionHandler extends BaseControllerExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 权限异常
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ResultVO handleShiroException(ShiroException ex) {
        String msg ;
        String code;
        if (ex instanceof IncorrectCredentialsException) {
            code = "AUTH-440";
            msg = "用户名或密码错误！";
        } else if (ex instanceof ExcessiveAttemptsException) {
            code = "AUTH-445";
            msg = "达到最大错误次数，请联系管理员或稍后再试！";
        } else if (ex instanceof UnauthenticatedException || ex instanceof AuthenticationException) {
            code = "AUTH-401";
            msg = "尚未登录或登录失效，请重新登录！";
        } else if (ex instanceof UnauthorizedException || ex instanceof AuthorizationException) {
            code = "AUTH-403";
            msg = "权限不足！";
        } else {
            code = "AUTH-444";
            msg = StringUtils.isBlank(ex.getMessage()) ? ex.getMessage() : ex.getCause().getMessage();
        }
        return ResultVO.failure(code, msg);
    }

    @ExceptionHandler(DeveloperException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO handleDeveloperException(DeveloperException ex) {
        return ResultVO.failure("400", ex.getMessage());
    }

    /**
     * SVN异常
     * @param ex
     * @return
     */
    @ExceptionHandler(SVNException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO handleSVNException(SVNException ex) {
        ex.printStackTrace();
        return ResultVO.error("SVN异常！" + ex.getMessage());
    }

}
