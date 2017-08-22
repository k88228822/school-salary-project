package com.yufeimen.application.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzhen on 2017/7/12.
 */
public class XLSModel {
    String title;
    ArrayList<List> content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<List> getContent() {
        return content;
    }

    public void setContent(ArrayList<List> content) {
        this.content = content;
    }

}
