package org.trendwa.eshop.basketservice.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import org.trendwa.eshop.basketservice.exception.CustomerBasketNotFoundException;
import org.trendwa.eshop.basketservice.model.ErrorInfo;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomerNotFoundExceptionHandler extends ResponseStatusExceptionHandler {

    @ExceptionHandler(CustomerBasketNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleCustomerBasketNotFoundException(CustomerBasketNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex, request);
    }

    protected ResponseEntity<ErrorInfo> buildErrorResponse(Exception exception, WebRequest webRequest) {

        ErrorInfo.ErrorInfoBuilder builder = ErrorInfo.builder();
        builder.statusCode(HttpStatus.NOT_FOUND.value());
        builder.errorCode(HttpStatus.NOT_FOUND.name());
        builder.message(exception.getMessage());
        builder.exceptionType(exception.getClass().getSimpleName());
        builder.timestamp(LocalDateTime.now());
        builder.details("Customer basket not found");
        builder.path(((ServletWebRequest) webRequest).getRequest().getRequestURI());

        return new ResponseEntity<>(builder.build(), HttpStatus.NOT_FOUND);

    }

}
