package com.crm.handler;

import com.crm.exception.CrmException;
import com.crm.utils.ResponseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(CrmException.class)
    public ResponseEntity handler(CrmException e) {
        return ResponseEntity.status(e.getExceptionEnums().getCode())
                             .body(new ResponseResult(e.getExceptionEnums()));
    }
}
