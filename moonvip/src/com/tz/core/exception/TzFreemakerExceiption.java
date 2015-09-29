package com.tz.core.exception;

/**
 * 
 * 处理freemaker报错的异常 TzFreemakerExceiption 
 * 创建人:xuchengfei
 * 时间：2015年5月14日-上午10:51:16
 * 
 * @version 1.0.0
 * 
 */
public class TzFreemakerExceiption extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 2332608236621015980L;

	public TzFreemakerExceiption() {
		super();
	}

	public TzFreemakerExceiption(String message) {
		super(message);
	}

	public TzFreemakerExceiption(Throwable cause) {
		super(cause);
	}

	public TzFreemakerExceiption(String message, Throwable cause) {
		super(message, cause);
	}

}
