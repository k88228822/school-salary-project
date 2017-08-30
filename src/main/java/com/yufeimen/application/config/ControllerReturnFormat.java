package com.yufeimen.application.config;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ControllerReturnFormat {
    private static Map<String, String> messageMap = new HashMap<>();

    //��ʼ��״̬��������˵��
    static {
        messageMap.put("0", "");

        messageMap.put("400", "Bad-Request!");
        messageMap.put("401", "NotAuthorization");
        messageMap.put("405", "Method-Not-Allowed");
        messageMap.put("406", "Not-Acceptable");
        messageMap.put("500", "Internal-Server-Error");

        messageMap.put("1000", "[������]����ʱ�쳣");
        messageMap.put("1001", "[������]��ֵ�쳣");
        messageMap.put("1002", "[������]��������ת���쳣");
        messageMap.put("1003", "[������]IO�쳣");
        messageMap.put("1004", "[������]δ֪�����쳣");
        messageMap.put("1005", "[������]����Խ���쳣");
        messageMap.put("1006", "[������]�����쳣");

        messageMap.put("2010", "ȱ�ٲ�����ֵΪ��");
        messageMap.put("2029", "�������Ϸ�");
        messageMap.put("2020", "��Ч��Token");
    }

    public static JSONObject retParam(int status, String errorMessage) {
        JSONObject ans = new JSONObject();
        ans.put("error", messageMap.get(String.valueOf(status)));
        ans.put("status",status);
        ans.put("message",errorMessage);
        return ans;
    }
}
