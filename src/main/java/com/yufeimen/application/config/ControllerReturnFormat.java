package com.yufeimen.application.config;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ControllerReturnFormat {
    private static Map<String, String> messageMap = new HashMap<>();

    //初始化状态码与文字说明
    static {
        messageMap.put("0", "");

        messageMap.put("400", "Bad-Request!");
        messageMap.put("401", "NotAuthorization");
        messageMap.put("405", "Method-Not-Allowed");
        messageMap.put("406", "Not-Acceptable");
        messageMap.put("500", "Internal-Server-Error");

        messageMap.put("1000", "[服务器]运行时异常");
        messageMap.put("1001", "[服务器]空值异常");
        messageMap.put("1002", "[服务器]数据类型转换异常");
        messageMap.put("1003", "[服务器]IO异常");
        messageMap.put("1004", "[服务器]未知方法异常");
        messageMap.put("1005", "[服务器]数组越界异常");
        messageMap.put("1006", "[服务器]网络异常");

        messageMap.put("2010", "缺少参数或值为空");
        messageMap.put("2029", "参数不合法");
        messageMap.put("2020", "无效的Token");
    }

    public static JSONObject retParam(int status, String errorMessage) {
        JSONObject ans = new JSONObject();
        ans.put("error", messageMap.get(String.valueOf(status)));
        ans.put("status",status);
        ans.put("message",errorMessage);
        return ans;
    }
}
