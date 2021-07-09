package ru.vlsu.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "cant connect to deleted portal")
public class BindNewsToDeletedPortalException extends RuntimeException {


}
