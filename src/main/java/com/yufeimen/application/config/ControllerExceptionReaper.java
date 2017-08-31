package com.yufeimen.application.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 异常增强，以JSON的形式返回给客服端
 * 异常增强类型：NullPointerException,RunTimeException,ClassCastException,
 * NoSuchMethodException,IOException,IndexOutOfBoundsException
 * 以及springmvc自定义异常等，如下：
 * SpringMVC自定义异常对应的status code
 * Exception                               HTTP Status Code
 * ConversionNotSupportedException         500 (Internal Server Error)
 * HttpMessageNotWritableException         500 (Internal Server Error)
 * HttpMediaTypeNotSupportedException      415 (Unsupported Media Type)
 * HttpMediaTypeNotAcceptableException     406 (Not Acceptable)
 * HttpRequestMethodNotSupportedException  405 (Method Not Allowed)
 * NoSuchRequestHandlingMethodException    404 (Not Found)
 * TypeMismatchException                   400 (Bad Request)
 * HttpMessageNotReadableException         400 (Bad Request)
 * MissingServletRequestParameterException 400 (Bad Request)
 */
@ControllerAdvice(annotations = {RestController.class})
public class ControllerExceptionReaper {
    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JSONObject runtimeExceptionHandler(RuntimeException ex) {

        return ControllerReturnFormat.retParam(1000, ex.getMessage());
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public JSONObject nullPointerExceptionHandler(NullPointerException ex) {
        ex.printStackTrace();
        return ControllerReturnFormat.retParam(1001, ex.getMessage());
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public JSONObject classCastExceptionHandler(ClassCastException ex) {
        ex.printStackTrace();
        return ControllerReturnFormat.retParam(1002, ex.getMessage());
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public JSONObject iOExceptionHandler(IOException ex) {
        ex.printStackTrace();
        return ControllerReturnFormat.retParam(1003, ex.getMessage());
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseBody
    public JSONObject noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        ex.printStackTrace();
        return ControllerReturnFormat.retParam(1004, ex.getMessage());
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public JSONObject indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        ex.printStackTrace();
        return ControllerReturnFormat.retParam(1005, ex.getMessage());
    }

    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public JSONObject requestNotReadable(HttpMessageNotReadableException ex) {
        System.out.println("400..requestNotReadable");
        ex.printStackTrace();
        return ControllerReturnFormat.retParam(400, ex.getMessage());
    }

    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public JSONObject requestTypeMismatch(TypeMismatchException ex) {
        System.out.println("400..TypeMismatchException");
        ex.printStackTrace();
        return ControllerReturnFormat.retParam(400, ex.getMessage());
    }

    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public JSONObject requestMissingServletRequest(MissingServletRequestParameterException ex) {
        System.out.println("400..MissingServletRequest");
        ex.printStackTrace();
        return ControllerReturnFormat.retParam(400, ex.getMessage());
    }

    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public JSONObject request405(HttpRequestMethodNotSupportedException ex) {
        System.out.println("405...");
        return ControllerReturnFormat.retParam(405, ex.getMessage());
    }

    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    public JSONObject request406(HttpMediaTypeNotAcceptableException ex) {
        System.out.println("406...");
        return ControllerReturnFormat.retParam(406, ex.getMessage());
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    @ResponseBody
    public JSONObject server500(RuntimeException ex) {
        System.out.println("500...");
        return ControllerReturnFormat.retParam(500, ex.getMessage());
    }

    //401错误
    @ExceptionHandler({AuthenticationException.class})
    @ResponseBody
    public JSONObject server401(AuthenticationException ex) {
        System.out.println("401...");
        return ControllerReturnFormat.retParam(401, "无权限访问");
    }
    //401错误
    @ExceptionHandler({InternalAuthenticationServiceException.class})
    @ResponseBody
    public JSONObject server401(InternalAuthenticationServiceException ex) {
        System.out.println("401...");
        return ControllerReturnFormat.retParam(401, ex.getMessage());
    }

}
