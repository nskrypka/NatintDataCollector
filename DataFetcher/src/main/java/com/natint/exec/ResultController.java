package com.natint.exec;

import com.natint.data.IData;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by skn on 09/10/2015.
 */
@Component
public class ResultController {

    private HashMap<Integer, List<IData>> resultMap;

    public ResultController (HashMap<Integer, List<IData>> resultMap) {
        this.resultMap = resultMap;
    }

    public void setResult (Integer id, List<IData> result) {
        resultMap.put(id, result);
    }

    public List<IData> getResult (Integer id) {
        if (resultMap.containsKey(id)) {
            return resultMap.get(id);
        } else {
            throw new IllegalArgumentException("There is no result for id : " + id + ". Please check its status is COMPLETE");
        }
    }
}
