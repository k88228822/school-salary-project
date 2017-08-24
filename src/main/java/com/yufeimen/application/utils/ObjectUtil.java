package com.yufeimen.application.utils;

import java.lang.reflect.Field;

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
        System.out.println("class属性长度:"+fields.length);
        for(int i=startNum;i<fields.length;i++){
            String name=fields[i].getName();
            String type=fields[i].getGenericType().toString();
            fields[i].setAccessible(true);
            switch (type.substring(6)){
                case "java.lang.String":
                    fields[i].set(model,(String)data[i-startNum]);
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
            }
        }

        return model;
    }
}
