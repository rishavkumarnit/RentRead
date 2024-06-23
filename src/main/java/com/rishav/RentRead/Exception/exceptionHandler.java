package com.rishav.RentRead.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class exceptionHandler {

    @ExceptionHandler(BlankException.class)
    public ResponseEntity<String> handleblankInput(BlankException blankException){
        return new ResponseEntity<>(blankException.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BooklimitException.class)
    public ResponseEntity<String> booklimitIussue(BooklimitException booklimitException){
        return new ResponseEntity<>(booklimitException.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotfoundException.class)
    public ResponseEntity<String> handleuserexists(NotfoundException notfoundException){
        return new ResponseEntity<>(notfoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<String> handlealreadyexistsInput(AlreadyExistsException alreadyExistsException){
        return new ResponseEntity<>(alreadyExistsException.getMessage(), HttpStatus.CONFLICT);
    }
}
