package com.natint.task;

import org.springframework.stereotype.Component;

/**
 * Created by skn on 16/10/2015.
 */
@Component
public class TaskFactory {

    public Task getInstance(String type) {
        switch (type.toLowerCase()) {
            case "ui":
                return new UiTask();
            case "email":
                return new EmailTask();
            case "api":
                return new ApiTask();
            default:
                throw new IllegalArgumentException("There is no service to process type " + type + ". Please use one of the following : ui, email, api");
        }
    }

}
