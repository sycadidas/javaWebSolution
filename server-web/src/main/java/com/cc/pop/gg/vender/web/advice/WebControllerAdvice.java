package com.cc.pop.gg.vender.web.advice;

import com.paipai.pop.jd.vender.common.domain.Response;
import com.paipai.pop.jd.vender.web.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller层的异常处理
 */
@ControllerAdvice
public class WebControllerAdvice {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebControllerAdvice.class);

    /**
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Response<String>> handleException(Throwable ex) {
        return renderResponse(null);
    }

    /**
     * 特殊处理
     * @param ex
     * @return
     */
    private ResponseEntity<Response<String>> getSpecialResponse(BindException ex) {
        BindException e = ex;
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        Response<String> response = Response.illegalRequestParameter("请求传值格式异常，请仔细检查");
        if (fieldError != null) {
            String field = fieldError.getField();
            Object rejectedValue = fieldError.getRejectedValue();
            if (rejectedValue == null) {
                response = Response.illegalRequestParameter("参数[" + field + "]的传值[" + rejectedValue + "]格式错误，请仔细检查");
            } else {
                String defaultMessage = fieldError.getDefaultMessage();
                response = Response.illegalRequestParameter("参数[" + field + "]的传值[" + defaultMessage + "]格式错误，请仔细检查");
            }

        }
        return renderResponse(response);
    }

    /**
     * 渲染响应
     *
     * @param response
     * @return
     */
    public ResponseEntity<Response<String>> renderResponse(Response<String> response) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
