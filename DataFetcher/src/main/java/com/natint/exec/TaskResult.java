package com.natint.exec;

import com.natint.data.Data;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by skn on 09/10/2015.
 */
@Resource
public class TaskResult {

    private HashMap<Integer, List<Data>> resultMap;

    public TaskResult(HashMap<Integer, List<Data>> resultMap) {
        this.resultMap = resultMap;
    }

    public void setResult (Integer id, List<Data> result) {
        resultMap.put(id, result);
    }

    public List<Data> getResult (Integer id) {
        if (resultMap.containsKey(id)) {
            return resultMap.get(id);
        } else {
            throw new IllegalArgumentException("There is no result for id : " + id + ". Please check its status is COMPLETE");
        }
    }
}
