package com.natint;

import com.google.gson.Gson;
import com.natint.configuration.Configs;
import com.natint.configuration.JsonPlaceholderRoute;
import com.natint.input.Parameters;
import com.natint.task.Task;
import com.natint.task.TaskExecutor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

/**
 * Created by ivaa on 10/7/2015.
 */
@Component
public class Main {

    @Autowired
    TaskExecutor taskExecutor;

    @Autowired
    Task task;

    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        ApplicationContext ctxJava = new AnnotationConfigApplicationContext(Configs.class, JsonPlaceholderRoute.class);
        Main main = ctxJava.getBean(Main.class);
        main.startProcessing();
        main.shutdown();
    }

    private void shutdown() throws InterruptedException {
        taskExecutor.shutdown();
        System.exit(0);
    }

    private void startProcessing() throws ExecutionException, InterruptedException {
        String inputParameters = System.getProperty("parameters");
        Parameters parameters = new Gson().fromJson(inputParameters, Parameters.class);
        task.init(parameters.getParams());
        int taskId = taskExecutor.execute(task);
        logger.info("New task with id " + taskId + " was generated for parameters : " + parameters.toString());
    }
}
