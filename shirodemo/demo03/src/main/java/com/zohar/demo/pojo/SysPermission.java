package com.zohar.demo.pojo;

import com.alibaba.fastjson.JSONObject;

public class SysPermission {
    private Integer id;

    private String url;

    private String name;

    private String perms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
