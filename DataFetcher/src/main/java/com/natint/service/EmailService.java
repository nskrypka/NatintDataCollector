package com.natint.service;

import com.natint.task.EmailTask;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by skn on 12/10/2015.
 */
public class EmailService extends Service {

    @Override
    public String getTask(Map<String, String> params) throws ExecutionException, InterruptedException {
        EmailTask task = (EmailTask) taskExecutor.initEmailTask(params);
        return String.valueOf(taskExecutor.execute(task));
    }
}
