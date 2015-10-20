package com.natint.api;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by skn on 16/10/2015.
 */
@Component
public class ApiFactory {

    private final ProducerTemplate producerTemplate;

    @Autowired
    public ApiFactory(ProducerTemplate producerTemplate) {

        this.producerTemplate = producerTemplate;
    }

    public Api getApi(Map<String, String> params) {
        return new Api(params, producerTemplate);
    }
}
