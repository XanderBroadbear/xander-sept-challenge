package com.xander.rest.exception;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
public class Handler {
    @SuppressWarnings({"unchecked","rawtypes"})
    @ControllerAdvice
    public class CustomExceptionHandler
    {
        @ExceptionHandler(ServletRequestBindingException.class)
        public final ResponseEntity<Object> handleHeaderException(HeadException ex, WebRequest request)
        {
            List<String> details = new ArrayList<>();
            details.add(ex.getLocalizedMessage());
            ErrorResponse error = new ErrorResponse("Bad Request", details);
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(HeadException.class)
        public final ResponseEntity<Object> handleAllExceptions(HeadException ex, WebRequest request)
        {
            List<String> details = new ArrayList<>();
            details.add(ex.getLocalizedMessage());
            ErrorResponse error = new ErrorResponse("Server Error", details);
            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
