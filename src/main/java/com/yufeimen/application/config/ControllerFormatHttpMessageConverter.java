package com.yufeimen.application.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Component
public class ControllerFormatHttpMessageConverter implements HttpMessageConverter<Object> {
    @Override
    public boolean canRead(Class<?> aClass, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> aClass, MediaType mediaType) {
        return true;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8);
    }

    @Override
    public Object read(Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Object o, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        PrintWriter cout = new PrintWriter(httpOutputMessage.getBody());
        if(o.toString().contains("error")&&o.toString().contains("status")){
            JSONObject ans = new JSONObject();
            ans.put("object", o);
            ans.put("successful", Boolean.FALSE);
            cout.write(JSON.toJSONString(ans, Boolean.TRUE));
        }else{
            JSONObject ans = new JSONObject();
            ans.put("object", o);
            ans.put("successful", Boolean.TRUE);
            cout.write(JSON.toJSONString(ans, Boolean.TRUE));
        }
        cout.close();
    }
}
