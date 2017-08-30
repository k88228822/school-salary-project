package com.yufeimen.application.utils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ObjectUtil<T> {
    /**
     *
     * @param model     被赋值对象
     * @param data      赋值数据
     * @param startNum  对象属性起始位置
     * @return
     * @throws IllegalAccessException
     */
    public T initData(T model,String[]data,int startNum) throws IllegalAccessException {
        Field[] fields=model.getClass().getDeclaredFields();
        for(int i=startNum;i<fields.length;i++){
            String name=fields[i].getName();
            String type=fields[i].getGenericType().toString();
            fields[i].setAccessible(true);
            switch (type.substring(6)){
                case "java.lang.String":
                    fields[i].set(model,data[i-startNum]);
                    break;
                case "java.lang.Integer":
                    fields[i].set(model,Integer.parseInt(data[i-startNum].equals("")?"0":data[i-startNum]));
                    break;
                case "java.lang.Double":
                    fields[i].set(model,Double.parseDouble(data[i-startNum].equals("")?"0":data[i-startNum]));
                    break;
                case "java.lang.Boolean":
                    fields[i].set(model,Boolean.parseBoolean(data[i-startNum].equals("")?"false":data[i-startNum]));
                    break;
                case "java.util.Date":
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        fields[i].set(model,sdf.parse(data[i-startNum]));
                    } catch (ParseException e) {
                        throw new RuntimeException("日期格式错误");
                    }
                    break;
            }
        }

        return model;
    }
}
