package com.natint.service;

import com.natint.task.ApiTask;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by skn on 09/10/2015.
 */
public class ApiService extends Service {

    @Override
    public String getTask(Map<String, String> params) throws ExecutionException, InterruptedException {
        ApiTask task = (ApiTask) taskExecutor.initApiTask(params);
        return String.valueOf(taskExecutor.execute(task));
    }
}
