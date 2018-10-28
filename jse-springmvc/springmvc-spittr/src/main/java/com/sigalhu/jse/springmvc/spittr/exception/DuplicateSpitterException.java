package com.sigalhu.jse.springmvc.spittr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Duplicate Spitter Username")
public class DuplicateSpitterException extends RuntimeException {
}
