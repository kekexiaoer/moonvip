package com.tz.core.exception;

/**
 * 系统业务异常
 * @author luocanfeng
 * @date 2011-6-30 10:20:46
 */
public class TzBusinessException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 2332608236621015980L;

	private String code;

	public TzBusinessException() {
		super();
	}

	public TzBusinessException(String message) {
		super(message);
	}

	public TzBusinessException(String code, String message) {
		super(message);
		this.code = code;
	}

	public TzBusinessException(Throwable cause) {
		super(cause);
	}

	public TzBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public TzBusinessException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
