package com.codigo04.uploadassets.api;

import com.codigo04.uploadassets.api.dto.BaseResponse;
import com.codigo04.uploadassets.exceptions.AppRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(AppRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleAppRuntimeException(AppRuntimeException ex) {
        log.error("AppRuntimeException: {}", ex.getMessage(), ex.getCause());
        return new BaseResponse(ex.getMessage());
    }

}
