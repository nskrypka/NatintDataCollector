package com.natint.task;

import com.natint.api.Api;
import com.natint.api.JsonPlaceholderApi;
import com.natint.exec.Status;

import java.util.Map;

/**
 * Created by skn on 09/10/2015.
 */
public class ApiTask extends Task {

    public ApiTask() {
        super();
    }

    private Api getSite() {
        switch (getParams().get("siteName").toUpperCase()){
            case "JSONPLACEHOLDER" :
                return new JsonPlaceholderApi(getParams());
            default:
                throw new IllegalArgumentException("Please provide correct site name. For example : JsonPlaceholder");
        }
    }

    @Override
    public void init(Map<String, String> params) {
        taskStatus.setStatus(getId(), Status.INITIALIZED);
        this.params = params;
        this.endpoint = getSite();
    }
}
