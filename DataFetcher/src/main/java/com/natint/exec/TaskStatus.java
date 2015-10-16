package com.natint.exec;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by skn on 09/10/2015.
 */
@Resource
public class TaskStatus {

    private HashMap<Integer, Status> statusMap;

    public TaskStatus(HashMap<Integer, Status> statusMap) {
        this.statusMap = statusMap;
    }

    public void setStatus (Integer id, Status status) {
        statusMap.put(id, status);
    }

    public Status getStatus (Integer id) {
        return statusMap.containsKey(id) ? statusMap.get(id) : Status.NOTFOUND;
    }
}
