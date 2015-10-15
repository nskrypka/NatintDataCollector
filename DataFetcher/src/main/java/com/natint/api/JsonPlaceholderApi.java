package com.natint.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.response.Response;
import com.natint.data.ApiData;
import com.natint.data.IData;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

import static com.jayway.restassured.RestAssured.when;

/**
 * Created by skn on 09/10/2015.
 */
public class JsonPlaceholderApi extends Api {

    Map<String, String> params;
    private String commentsLink = System.getProperty("NATINT_GET_COMMENTS_LINK");
    private Logger logger = Logger.getLogger(this.getClass());

    public JsonPlaceholderApi(Map<String, String> params){
        this.params = params;
    }

    @Override
    public List<IData> collectData() {
        Integer postId = Integer.parseInt(params.get("postId"));
        Response response = when().get(commentsLink, postId).then().statusCode(200).extract().response();
        List<IData> resultFromJson = new Gson().fromJson(response.asString(), new TypeToken<List<ApiData>>(){}.getType());
        for (IData apiData : resultFromJson){
            logger.info("ApiData : " + apiData.toString());
        }
        return resultFromJson;
    }
}
