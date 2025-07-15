package com.hulkhiretech.payments.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NullPointerException extends RuntimeException {
	
	
private String msg;
private String errorCode;
	
}
