package me.simple.sys.controller;

import com.google.common.collect.Maps;
import me.simple.domain.CurrentUser;
import me.simple.web.method.support.LoginedUser;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import java.util.Map;

/**
 * Created by chensheng on 17/5/25.
 */

@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(value = {
            TypeMismatchException.class,
            BindException.class,
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class,
            MissingServletRequestPartException.class,
    })
    @ResponseBody
    public ResponseEntity<Map<String, Object>> e400(Exception exception) {
        Map<String, Object> body = Maps.newHashMap();
        body.put("success", false);
        body.put("data", exception.getMessage());
        body.put("message", HttpStatus.BAD_REQUEST.getReasonPhrase());
        return ResponseEntity.badRequest().body(body);
    }


    @ExceptionHandler(value = {
            NoHandlerFoundException.class,
    })
    @ResponseBody
    public ResponseEntity<Map<String, Object>> e404(Exception exception) {
        Map<String, Object> body = Maps.newHashMap();
        body.put("success", false);
        body.put("data", exception.getMessage());
        body.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());
        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,
    })
    @ResponseBody
    public ResponseEntity<Map<String, Object>> e405(Exception exception) {
        Map<String, Object> body = Maps.newHashMap();
        body.put("success", false);
        body.put("data", exception.getMessage());
        body.put("message", HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
        return new ResponseEntity(body, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = {
            HttpMediaTypeNotAcceptableException.class,
    })
    @ResponseBody
    public ResponseEntity<Map<String, Object>> e406(Exception exception) {
        Map<String, Object> body = Maps.newHashMap();
        body.put("success", false);
        body.put("data", exception.getMessage());
        body.put("message", HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
        return new ResponseEntity(body, HttpStatus.NOT_ACCEPTABLE);
    }


    @ExceptionHandler(value = {
            HttpMediaTypeNotSupportedException.class,
    })
    @ResponseBody
    public ResponseEntity<Map<String, Object>> e415(Exception exception) {
        Map<String, Object> body = Maps.newHashMap();
        body.put("success", false);
        body.put("data", exception.getMessage());
        body.put("message", HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase());
        return new ResponseEntity(body, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }


    @ExceptionHandler(value = {
            ConversionNotSupportedException.class,
            HttpMessageNotWritableException.class,
    })
    @ResponseBody
    public ResponseEntity<Map<String, Object>> e500(Exception exception) {
        Map<String, Object> body = Maps.newHashMap();
        body.put("success", false);
        body.put("data", exception.getMessage());
        body.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return new ResponseEntity(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = {
            DataAccessException.class,
    })
    @ResponseBody
    public ResponseEntity<Map<String, Object>> edb(Exception exception) {
        Map<String, Object> body = Maps.newHashMap();
        body.put("success", false);
        body.put("data", exception.getMessage());
        body.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return new ResponseEntity(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = {
            Exception.class,
    })
    @ResponseBody
    public ResponseEntity<Map<String, Object>> eserver(Exception exception) {
        Map<String, Object> body = Maps.newHashMap();
        body.put("success", false);
        body.put("data", exception.getMessage());
        body.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return new ResponseEntity(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
