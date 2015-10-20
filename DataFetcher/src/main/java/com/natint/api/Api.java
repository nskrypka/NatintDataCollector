package com.natint.api;

import com.natint.data.Data;
import com.natint.site.Endpoint;
import org.apache.camel.ProducerTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by skn on 09/10/2015.
 */
public class Api extends Endpoint {
    private final Map<String, String> params;
    private final ProducerTemplate producerTemplate;

    public Api(Map<String, String> params, ProducerTemplate producerTemplate){
        this.params = params;
        this.producerTemplate = producerTemplate;
    }


    @Override
    public List<Data> collectData() {
        producerTemplate.requestBody("direct:start", System.getProperty("parameters"));
        return new ArrayList<>();
    }
}
