package com.natint.input;

import java.util.Map;

/**
 * Created by skn on 15/10/2015.
 */
public class Parameters {
    String type;
    String url;
    String user;
    String password;
    Map<String, String> params;
    String site;
    String brand;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String
    toString() {
        return "Parameters{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", params=" + params +
                ", site='" + site + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
