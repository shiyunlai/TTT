package org.tis.tools.core.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.tis.tools.core.exception.ToolsRuntimeException;
import org.tis.tools.core.exception.WebAppException;
import org.tis.tools.model.common.ResultVO;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.Set;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author
 * @date 2018/04/18
 */
public class BaseControllerExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 用于处理通用异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO bindException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMessage = "数据验证失败:";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage += fieldError.getDefaultMessage() + ", ";
        }
        log.error("参数验证失败:", e);
        return ResultVO.error("400", errorMessage);
    }

    /**
     * 用于处理通用异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO methodValidateException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        String errorMessage = "参数验证失败:";
        for (ConstraintViolation<?> violation : violations) {
            errorMessage += violation.getMessage();
        }
        log.error("参数验证失败:", e);
        return ResultVO.error("400", errorMessage);
    }

    /**
     * 用于处理通用异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResultVO messageConversionException(HttpMessageConversionException e) {
        log.error("请求数据不合法:", e);
        return ResultVO.error("400", "请求数据不合法！");
    }

    /**
     * 服务异常处理
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ToolsRuntimeException.class)
    public ResultVO handleServiceRequestError(ToolsRuntimeException e) {
        log.error("服务异常:", e);
        return ResultVO.failure(e.getCode(), e.getMessage());
    }


    /**
     * WebApp层异常
     */
    @ExceptionHandler(WebAppException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO handleWebAppException(WebAppException e) {
        log.error("请求数据不合法:", e);
        return ResultVO.error(e.getCode(), e.getMessage());
    }


    /**
     * SQL错误
     */
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO handleSQLException(SQLException e) {
        log.error("SQL错误:", e);
        // SQL堆栈信息包含敏感信息，不返回前端，在日志中可以查看详细信息
        return ResultVO.error("内部服务发生了SQL错误！");
    }

    /**
     * SQL错误
     */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO handleDataAccessException(DataAccessException e) {
        log.error("SQL错误:", e);
        // SQL堆栈信息包含敏感信息，不返回前端，在日志中可以查看详细信息
        return ResultVO.error("内部服务发生了SQL错误！");
    }

    /**
     * 未知异常
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO handleUnexpectedServerError(Exception ex) {
        log.error("未知异常:", ex);
        return ResultVO.error("内部服务发生了未知错误！");
    }

}
