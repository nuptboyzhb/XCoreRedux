package com.example.haibozheng.myapplication.model;

import java.io.Serializable;

/**
 * @version mochuan.zhb on 16/9/28.
 * @Author Zheng Haibo
 * @Mail mochuan.zhb@alibaba-inc.com
 * @Company Alibaba Group
 * @Description model
 */
public class Title implements Serializable{

    public Title(){

    }

    public Title(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    private String categoryTitle;

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
