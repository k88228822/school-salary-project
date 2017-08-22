package com.yufeimen.application;

import com.yufeimen.application.model.Salary;
import com.yufeimen.application.utils.ObjectUtil;

public class test {
    public static void main(String[]args) throws IllegalAccessException {
        String[]data=new String[30];
        for(int i=0;i<data.length;i++){
           data[i]=i+".23"+i;
        }
        Salary salary=new Salary();
        salary=new ObjectUtil<Salary>().initData(salary,data,6);
        System.out.println(salary);
    }
}
