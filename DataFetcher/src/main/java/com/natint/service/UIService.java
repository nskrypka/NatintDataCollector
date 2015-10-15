package com.natint.service;

import com.natint.task.UiTask;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by ivaa on 10/6/2015.
 */
public class UIService extends Service {

    @Override
    public String getTask(Map<String, String> params) throws ExecutionException, InterruptedException {
        UiTask task = (UiTask) taskExecutor.initUiTask(params);
        return String.valueOf(taskExecutor.execute(task));
    }

}
