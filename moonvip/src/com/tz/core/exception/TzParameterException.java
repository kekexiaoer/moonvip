/**
 * @(#)ParameterException.java 2011-12-20 Copyright 2011 it.kedacom.com, Inc.
 *                             All rights reserved.
 */

package com.tz.core.exception;

/**
 * (用一句话描述类的主要功能)
 * @author huangchunhua
 * @date 2011-12-20
 */

public class TzParameterException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 6417641452178955756L;

	public TzParameterException() {
		super();
	}

	public TzParameterException(String message) {
		super(message);
	}

	public TzParameterException(Throwable cause) {
		super(cause);
	}

	public TzParameterException(String message, Throwable cause) {
		super(message, cause);
	}
}
