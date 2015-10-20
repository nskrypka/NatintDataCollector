package com.natint.configuration;

import com.google.gson.Gson;
import com.natint.input.Parameters;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.util.Map;

/**
 * Created by skn on 16/10/2015.
 */
public class JsonPlaceholderRoute extends RouteBuilder {

    private String todosLink = "http://jsonplaceholder.typicode.com/todos?userId=${header.postId}";
    private String getCommentsLink = "http://jsonplaceholder.typicode.com/posts/${header.postId}/comments";

    private Processor paramsProcessor = new Processor() {
        @Override
        public void process(Exchange exchange) throws Exception {
            String body = exchange.getIn().getBody(String.class);
            Parameters parameters = new Gson().fromJson(body, Parameters.class);
            Message out = exchange.getOut();
            out.setHeader("type", parameters.getType());
            Map<String, String> params = parameters.getParams();
            out.setHeader("params", params);
            for (String key : params.keySet()) {
                out.setHeader(key, params.get(key));
            }
            out.setBody(body);
        }
    };

    @Override
    public void configure() throws Exception {
        from("direct:start").process(paramsProcessor).
                choice().
                when(header("type").isEqualToIgnoreCase("api")).to("direct:api").endChoice();

        from("direct:api").process(paramsProcessor).
                choice().
                when(header("searchCriteria").contains("comments")).
                setHeader(Exchange.HTTP_METHOD, constant("GET")).enrich().simple(getCommentsLink).to("direct:out").endChoice().
                otherwise().
                setHeader(Exchange.HTTP_METHOD, constant("GET")).enrich().simple(todosLink).to("direct:out").endChoice();

        from("direct:out").process(new ProcessReport()).to("file:DataFetcher/target/google").to("stream:out");
    }

    private class ProcessReport implements Processor {

        public void process(Exchange exchange) {
            String body = exchange.getIn().getBody(String.class);
            exchange.getOut().setBody(body);
            exchange.getOut().setHeader(Exchange.FILE_NAME, "message.html");
        }
    }
}
