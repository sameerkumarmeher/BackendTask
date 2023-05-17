package com.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SupervisorNotFoundException extends RuntimeException {

    public SupervisorNotFoundException() {
        super("Supervisor Not Found ");
    }

    public SupervisorNotFoundException(String message) {
        super(message);
    }
}
