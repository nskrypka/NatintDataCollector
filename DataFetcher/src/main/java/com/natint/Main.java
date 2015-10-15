package com.natint;

import com.google.gson.Gson;
import com.natint.configuration.Configs;
import com.natint.input.Parameters;
import com.natint.service.ApiService;
import com.natint.service.EmailService;
import com.natint.service.Service;
import com.natint.service.UIService;
import com.natint.task.TaskExecutor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Created by ivaa on 10/7/2015.
 */
@Component
public class Main {

    @Autowired
    private ApiService apiService;

    @Autowired
    TaskExecutor taskExecutor;

    @Autowired
    private UIService uiService;

    @Autowired
    private EmailService emailService;

    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Configs.class);
        logger.info("In DataFetcher module Main.class");
        loadProperties();

        Main main = ctx.getBean(Main.class);
        main.startProcessing();
        main.shutdown();
    }

    private static void loadProperties() throws IOException {
        Properties properties = new Properties(System.getProperties());
        properties.load(Main.class.getClassLoader().getResourceAsStream("application.properties"));
        System.setProperties(properties);
    }

    private void shutdown() throws InterruptedException {
        taskExecutor.shutdown();
        System.exit(0);
    }

    private void startProcessing() throws ExecutionException, InterruptedException {
        String inputParameters = System.getProperty("parameters");
        Parameters parameters = new Gson().fromJson(inputParameters, Parameters.class);
        Service service = getService(parameters.getType());
        String taskId = service.getTask(parameters.getParams());
        logger.info("New task with id " + taskId + " was generated for parameters : " + parameters.toString());
    }

    private Service getService(String type) {
        switch (type) {
            case "ui":
                return uiService;
            case "email":
                return emailService;
            case "api":
                return apiService;
            default:
                throw new IllegalArgumentException("There is no service to process type " + type + ". Please use one of the following : ui, email, api");
        }
    }
}
