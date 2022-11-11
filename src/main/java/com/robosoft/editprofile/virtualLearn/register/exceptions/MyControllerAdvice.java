package com.robosoft.editprofile.virtualLearn.register.exceptions;


import com.twilio.exception.ApiException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleEmptyResult(EmptyResultDataAccessException exception){
        return new ResponseEntity<String>("Input field is incorrect", HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<String> handleEmptyResult(ParseException exception){
        return new ResponseEntity<String>("Send Date in dd/mm/yyyy format", HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handleApiException(ApiException exception){
        return new ResponseEntity<String>("Please Enter Valid Phone Number", HttpStatus.BAD_GATEWAY);
    }

}
