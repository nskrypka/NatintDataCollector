package com.natint.exec;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by skn on 09/10/2015.
 */
@Component
public class StatusController {

    private HashMap<Integer, Status> statusMap;

    public StatusController (HashMap<Integer, Status> statusMap) {
        this.statusMap = statusMap;
    }

    public void setStatus (Integer id, Status status) {
        statusMap.put(id, status);
    }

    public Status getStatus (Integer id) {
        return statusMap.containsKey(id) ? statusMap.get(id) : Status.NOTFOUND;
    }
}
