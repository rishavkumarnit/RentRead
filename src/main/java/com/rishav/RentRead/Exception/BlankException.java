package com.rishav.RentRead.Exception;



import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;



@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BlankException extends RuntimeException {
    public BlankException(String message) {
        super(message);
    }
}