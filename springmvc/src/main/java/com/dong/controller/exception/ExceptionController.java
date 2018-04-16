package com.dong.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(IOException.class)
    public String ioException(){
        return "error/500";
    }
}
