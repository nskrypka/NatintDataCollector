package com.natint.task;

import com.natint.api.ApiFactory;
import com.natint.exec.Status;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by skn on 09/10/2015.
 */
public class ApiTask extends Task {

    @Autowired
    ApiFactory apiFactory;

    public ApiTask() {
        super();
    }

    @Override
    public void init(Map<String, String> params) {
        taskStatus.setStatus(getId(), Status.INITIALIZED);
        this.params = params;
        this.endpoint = apiFactory.getApi(params);
    }
}
