package com.airton.psjava.exception;

import com.airton.psjava.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourcesNotFoundException.class) //intercepta qualquer excessão do tipo instanciado
    public ResponseEntity<ErrorDTO> resourceNotFound(ResourcesNotFoundException e,
                                                     HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorDTO err = new ErrorDTO(status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //intercepta qualquer excessão do tipo instanciado
    public ResponseEntity<ErrorDTO> methodArgumentNotValid(MethodArgumentNotValidException e,
                                                           HttpServletRequest request) {
        String error = "Request incomplete";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDTO err = new ErrorDTO(status.value(), error, e.getAllErrors().toString(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }


}
