/**
 * 
 */
package org.tis.tools.core.exception;



import org.tis.tools.core.exception.i18.I18NException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;

/**
 * 
 * Tools检查异常.</br>
 * 抛出本异常的地方表示系统执行失败了！</br>
 * 所有业务域的服务处理时，失败抛出的异常必须继承本类.</br>
 * 其他业务域中的检查异常都必须集成于本类.</br>
 * @author megapro
 *
 */
public class ToolsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8575481651458300491L;


	private I18NException i18nException = null;

	/**
	 * 获取异常码.<br>
	 *
	 * @return 异常码
	 */
	public String getCode() {
		return i18nException.getCode();
	}

	/**
	 * 获取异常参数.<br>
	 *
	 * @return 异常参数
	 */
	public Object[] getParams() {
		return i18nException.getParams();
	}

	/**
	 * 获取异常.<br>
	 *
	 * @return 异常
	 */
	public Throwable getException() {
		return i18nException.getException();
	}

	/**
	 * 按照指定的locale获取本地化的消息.<br>
	 *
	 * @param locale
	 *            地区
	 * @return 本地化的消息
	 */
	public String getMessage(Locale locale) {
		return i18nException.getLocalizedMessage(locale);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		return i18nException.getLocalizedMessage();
	}

	/**
	 * 获取栈信息.<br>
	 *
	 * @return 栈信息
	 */
	public String toStackTraceString() {
		StringWriter sw = new StringWriter();
		printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	/**
	 * 构造函数.<br>
	 */
	@Deprecated
	public ToolsException() {
		i18nException = new I18NException();
	}

	/**
	 * 构造函数.<br>
	 *
	 * @param code
	 *            异常码
	 */
	public ToolsException(String code) {
		i18nException = new I18NException(code);
	}

	/**
	 * 构造函数.<br>
	 *
	 * @param code
	 *            异常码
	 * @param params
	 *            参数值
	 */
	public ToolsException(String code, Object[] params) {
		i18nException = new I18NException(code, params);
	}

	/**
	 * 构造函数.<br>
	 *
	 * @param code
	 *            异常码
	 * @param message
	 *            消息
	 */
	public ToolsException(String code, String message) {
		i18nException = new I18NException(code, message);
	}

	/**
	 * 构造函数.<br>
	 *
	 * @param code
	 *            异常码
	 * @param cause
	 *            异常原因
	 */
	public ToolsException(String code, Throwable cause) {
		super(cause);
		i18nException = new I18NException(code, cause);
	}

	/**
	 * 构造函数.<br>
	 *
	 * @param code
	 *            异常码
	 * @param params
	 *            参数值
	 * @param message
	 *            异常消息
	 *
	 */
	public ToolsException(String code, Object[] params, String message) {
		i18nException = new I18NException(code, params, message);
	}

	/**
	 * 构造函数.<br>
	 *
	 * @param code
	 *            异常码
	 * @param message
	 *            异常消息
	 * @param cause
	 *            异常原因
	 */
	public ToolsException(String code, String message, Throwable cause) {
		super(cause);
		i18nException = new I18NException(code, message, cause);
	}

	/**
	 * 构造函数.<br>
	 *
	 * @param code
	 *            异常码
	 * @param params
	 *            参数值
	 * @param cause
	 *            异常原因
	 */
	public ToolsException(String code, Object[] params, Throwable cause) {
		super(cause);
		i18nException = new I18NException(code, params, cause);
	}

	/**
	 * 构造函数.<br>
	 *
	 * @param code
	 *            异常码
	 * @param params
	 *            参数值
	 * @param message
	 *            异常消息
	 *
	 * @param cause
	 *            异常原因
	 */
	public ToolsException(String code, Object[] params, String message,
			Throwable cause) {
		super(cause);
		i18nException = new I18NException(code, params, message, cause);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Throwable#toString()
	 */
	public String toString() {
		return i18nException.toString();
	}
}
