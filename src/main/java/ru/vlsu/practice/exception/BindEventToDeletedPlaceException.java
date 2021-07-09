package ru.vlsu.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "can not connect to deleted place")
public class BindEventToDeletedPlaceException extends RuntimeException {
}
