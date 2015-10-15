package com.natint.configuration;

import com.natint.Main;
import com.natint.data.IData;
import com.natint.exec.ResultController;
import com.natint.exec.Status;
import com.natint.exec.StatusController;
import com.natint.service.ApiService;
import com.natint.service.EmailService;
import com.natint.service.UIService;
import com.natint.task.ApiTask;
import com.natint.task.EmailTask;
import com.natint.task.TaskExecutor;
import com.natint.task.UiTask;
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
    public ResultController resultController () {
        return new ResultController(new HashMap<Integer, List<IData>>());
    }

    @Bean
    public StatusController statusController () {
        return new StatusController(new HashMap<Integer, Status>());
    }

    @Bean (destroyMethod = "shutdown")
    public TaskExecutor taskExecutor(){
        return new TaskExecutor(Executors.newFixedThreadPool(Integer.parseInt(poolSize)));
    }

    @Bean
    public ApiService apiService(){
        return new ApiService();
    }

    @Bean
    public UIService uiService(){
        return new UIService();
    }

    @Bean
    public Main main(){
        return new Main();
    }

    @Bean
    public EmailService emailService(){
        return new EmailService();
    }

    @Bean
    public UiTask uiTask(){
        return new UiTask();
    }

    @Bean
    public ApiTask apiTask(){
        return new ApiTask();
    }

    @Bean
    public EmailTask emailTask(){
        return new EmailTask();
    }

}
