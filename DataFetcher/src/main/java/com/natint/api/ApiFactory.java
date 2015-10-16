package com.natint.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by skn on 16/10/2015.
 */
@Component
public class ApiFactory {

    private String getCommentsLink;

    @Autowired
    public ApiFactory(String getCommentsLink) {
        this.getCommentsLink = getCommentsLink;
    }

    public Api getApi(Map<String, String> params) {
        switch (params.get("siteName").toUpperCase()){
            case "JSONPLACEHOLDER" :
                return new JsonPlaceholderApi(getCommentsLink, params);
            default:
                throw new IllegalArgumentException("Please provide correct site name. For example : JsonPlaceholder");
        }
    }
}
