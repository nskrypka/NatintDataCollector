package com.natint.configuration;

import com.google.gson.Gson;
import com.natint.Main;
import com.natint.data.Data;
import com.natint.exec.TaskResult;
import com.natint.exec.Status;
import com.natint.exec.TaskStatus;
import com.natint.input.Parameters;
import com.natint.task.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * Created by skn on 08/10/2015.
 */
@Configuration
@ComponentScan(basePackages = {"com.natint.*"})
@PropertySource("classpath:/application.properties")
public class Configs {

    @Value("${pool.size}")
    private String poolSize;

    private Logger logger = Logger.getLogger(this.getClass());

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public TaskResult taskResult () {
        return new TaskResult(new HashMap<Integer, List<Data>>());
    }

    @Bean
    public TaskStatus taskStatus () {
        return new TaskStatus(new HashMap<Integer, Status>());
    }

    @Bean (destroyMethod = "shutdown")
    public TaskExecutor taskExecutor(){
        return new TaskExecutor(Executors.newFixedThreadPool(Integer.parseInt(poolSize)));
    }

    @Bean
    public Main main(){
        return new Main();
    }

    @Bean
    public Task task(){
        String inputParameters = System.getProperty("parameters");
        Parameters parameters = new Gson().fromJson(inputParameters, Parameters.class);
        return (new TaskFactory()).getInstance(parameters.getType());
    }

}
