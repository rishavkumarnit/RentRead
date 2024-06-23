package com.rishav.RentRead.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;



@ResponseStatus(value = HttpStatus.CONFLICT)
public class NotfoundException extends RuntimeException {
    public NotfoundException(String message) {
        super(message);
    }
}