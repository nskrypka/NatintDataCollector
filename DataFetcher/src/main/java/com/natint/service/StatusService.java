package com.natint.service;

import com.natint.data.Data;
import com.natint.exec.TaskResult;
import com.natint.exec.Status;
import com.natint.exec.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by skn on 09/10/2015.
 */
public class StatusService  {

    @Autowired
    TaskStatus taskStatus;
    @Autowired
    TaskResult taskResult;

    public List<Data> getResult (Integer id) {
        return taskResult.getResult(id);
    }

    public Status getStatus(Integer id) {
        return taskStatus.getStatus(id);
    }
}
