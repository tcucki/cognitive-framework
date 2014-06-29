package com.cognitive.framework.exception;

public class CognitiveRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1037659281181002758L;

	public CognitiveRuntimeException() {
		super();
	}
	
	public CognitiveRuntimeException(String message) {
		super(message);
	}
	
	public CognitiveRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public CognitiveRuntimeException(Throwable cause) {
        super(cause);
    }
}
