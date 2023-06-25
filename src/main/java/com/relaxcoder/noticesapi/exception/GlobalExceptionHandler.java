package com.relaxcoder.noticesapi.exception;

import com.relaxcoder.noticesapi.dtos.ErrorDetailResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailResponse> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                               WebRequest webRequest){
        ErrorDetailResponse errorDetailResponse = buildErrorResponse(exception, webRequest);

        return new ResponseEntity<>(errorDetailResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorDetailResponse> handleCustomException(CustomException customException,
                                                                     WebRequest webRequest){

        ErrorDetailResponse errorDetailResponse = buildErrorResponse(customException, webRequest);

        return new ResponseEntity<>(errorDetailResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailResponse> handleGlobalException(Exception exception,
                                                                     WebRequest webRequest){

        ErrorDetailResponse errorDetailResponse = buildErrorResponse(exception, webRequest);

        return new ResponseEntity<>(errorDetailResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetailResponse> handleAccessDeniedException(AccessDeniedException exception,
                                                                    WebRequest webRequest){
        ErrorDetailResponse errorDetailResponse = buildErrorResponse(exception, webRequest);

        return new ResponseEntity<>(errorDetailResponse, HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                               HttpHeaders httpHeaders,
                                                                               HttpStatusCode httpStatusCode,
                                                                               WebRequest webRequest){
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors()
                .forEach(objectError -> {
                    String fieldName = ((FieldError)objectError).getField();
                    String message = objectError.getDefaultMessage();
                    errors.put(fieldName, message);
                });


        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    private ErrorDetailResponse buildErrorResponse(Exception exception,
                                                   WebRequest webRequest){
        return ErrorDetailResponse
                .builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .details(webRequest.getDescription(false))
                .build();
    }
}
