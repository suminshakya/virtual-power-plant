package com.proshore.vpps.exception_handler;

import com.proshore.vpps.custom_exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionPointcut extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final HttpServletRequest httpServletRequest) {
        ErrorMessage errorResponse = new ErrorMessage();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setApiPath(httpServletRequest.getRequestURI());

        ex.getConstraintViolations().forEach(violation ->  errorResponse
                                                .getErrors().add(violation.getMessage()));

        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}
