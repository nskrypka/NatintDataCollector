package com.natint.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.response.Response;
import com.natint.data.ApiData;
import com.natint.data.Data;
import org.apache.camel.ProducerTemplate;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

import static com.jayway.restassured.RestAssured.when;

/**
 * Created by skn on 09/10/2015.
 */
public class JsonPlaceholderApi extends Api {

    Map<String, String> params;
    private String commentsLink;
    private Logger logger = Logger.getLogger(this.getClass());

    public JsonPlaceholderApi(String getCommentsLink, Map<String, String> params, ProducerTemplate producerTemplate) {
        super(params, producerTemplate);
    }

    @Override
    public List<Data> collectData() {
        Integer postId = Integer.parseInt(params.get("postId"));
        Response response = when().get(commentsLink, postId).then().statusCode(200).extract().response();
        List<Data> resultFromJson = new Gson().fromJson(response.asString(), new TypeToken<List<ApiData>>(){}.getType());
        for (Data apiData : resultFromJson){
            logger.info("ApiData : " + apiData.toString());
        }
        return resultFromJson;
    }
}
