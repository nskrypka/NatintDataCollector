package com.natint.task;

import com.natint.data.Data;
import com.natint.exec.Status;
import com.natint.exec.TaskResult;
import com.natint.exec.TaskStatus;
import com.natint.site.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by skn on 08/10/2015.
 */
@Component
public abstract class Task implements Runnable {

    @Autowired
    protected TaskResult taskResult;

    @Autowired
    protected TaskStatus taskStatus;

    private AtomicInteger nextId = new AtomicInteger(1);

    private final int id;
    protected Map<String, String> params;
    protected Endpoint endpoint;

    public Task() {
        this.id = nextId.getAndIncrement();
    }

    public abstract void init(Map<String, String> params);

    public int getId() {
        return id;
    }

    public Map<String, String> getParams() {
        return params;
    }

    @Override
    public void run() {
        System.out.println("Executing task " + getId());
        taskStatus.setStatus(getId(), Status.INPROGRESS);
        try {
            List<Data> result = endpoint.collectData();
            taskResult.setResult(getId(), result);
            taskStatus.setStatus(getId(), Status.COMPLETE);
        } catch (Exception e) {
            taskStatus.setStatus(getId(), Status.ERROR);
            throw new IllegalStateException(e);
        }
    }
}
