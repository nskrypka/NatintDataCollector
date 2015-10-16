package com.natint.configuration;

import com.google.gson.Gson;
import com.natint.Main;
import com.natint.api.ApiFactory;
import com.natint.data.Data;
import com.natint.email.EmailFactory;
import com.natint.exec.Status;
import com.natint.exec.TaskResult;
import com.natint.exec.TaskStatus;
import com.natint.input.Parameters;
import com.natint.site.SiteFactory;
import com.natint.task.Task;
import com.natint.task.TaskExecutor;
import com.natint.task.TaskFactory;
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

    @Value("${NATINT_EBAY_LINK}")
    private String ebayLink;

    @Value("${NATINT_AMAZON_LINK}")
    private String amazonLink;

    @Value("${NATINT_EMAIL_LOGIN}")
    private String gmailLogin;

    @Value("${NATINT_EMAIL_PASSWORD}")
    private String gmailPassword;

    @Value("${NATINT_EMAIL_PROTOCOL}")
    private String emailProtocol;

    @Value("${NATINT_EMAIL_META_UA}")
    private String metaLogin;

    @Value("${NATINT_PASSWORD_META_UA}")
    private String metaPassword;

    @Value("${NATINT_GET_COMMENTS_LINK}")
    private String getCommentsLink;

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
        Parameters parameters = getParameters();
        return (new TaskFactory()).getInstance(parameters.getType());
    }

    private Parameters getParameters() {
        String inputParameters = System.getProperty("parameters");
        return new Gson().fromJson(inputParameters, Parameters.class);
    }

    @Bean
    public SiteFactory siteFactory(){
        return new SiteFactory(ebayLink, amazonLink);
    }

    @Bean
    public EmailFactory emailFactory(){
        return new EmailFactory(gmailLogin, gmailPassword, emailProtocol, metaLogin, metaPassword);
    }

    @Bean
    public ApiFactory apiFactory(){
        return new ApiFactory(getCommentsLink);
    }

}
