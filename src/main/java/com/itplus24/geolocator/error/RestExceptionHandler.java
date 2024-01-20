package com.itplus24.geolocator.error;

import com.itplus24.geolocator.error.exceptions.BadRequestException;
import com.itplus24.geolocator.error.exceptions.SomethingWentWrongException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;

import static org.springframework.http.HttpStatus.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var message = "Invalid Input";

        System.out.println(message);

        var subErrors = new ArrayList<ApiSubError>();

        for (var fieldError : ex.getFieldErrors()) {
            var name = fieldError.getField();
            var msg = fieldError.getDefaultMessage();
            var subError = new ApiSubError(name, msg);
            subErrors.add(subError);
        }

        var apiError = new ApiError(UNPROCESSABLE_ENTITY, message, subErrors);
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var message = "Invalid Input";

        var subErrors = new ArrayList<ApiSubError>();
        subErrors.add(new ApiSubError(ex.getParameterName(), ex.getParameterName() + " parameter is missing"));

        var apiError = new ApiError(UNPROCESSABLE_ENTITY, message, subErrors);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        var message = "invalid Value";

        var subErrors = new ArrayList<ApiSubError>();

        for (var violation : ex.getConstraintViolations()) {
            var name = violation.getPropertyPath().toString();
            var msg = violation.getMessage();

            var subError = new ApiSubError(name, msg);

            subErrors.add(subError);
        }

        var apiError = new ApiError(BAD_REQUEST, message, subErrors);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        var message = ex.getMessage();
        var apiError = new ApiError(NOT_FOUND, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityExistsException.class)
    protected ResponseEntity<Object> handleEntityExists(EntityExistsException ex) {
        var message = ex.getMessage();
        var apiError = new ApiError(BAD_REQUEST, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequest(BadRequestException ex) {
        var message = ex.getMessage();
        var apiError = new ApiError(BAD_REQUEST, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(SomethingWentWrongException.class)
    protected ResponseEntity<Object> handleBadRequest(SomethingWentWrongException ex) {
        var message = ex.getMessage();
        var apiError = new ApiError(INTERNAL_SERVER_ERROR, message);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
