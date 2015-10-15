package com.natint.task;

import com.natint.data.IData;
import com.natint.exec.ResultController;
import com.natint.exec.Status;
import com.natint.exec.StatusController;
import com.natint.site.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by skn on 08/10/2015.
 */
public abstract class Task implements Runnable {

    @Autowired
    protected ResultController resultController;

    @Autowired
    protected StatusController statusController;

    private static int nextId = 1;

    private final int id;
    protected Map<String, String> params;
    protected Endpoint endpoint;

    public Task() {
        this.id = nextId++;
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
        statusController.setStatus(getId(), Status.INPROGRESS);
        try {
            List<IData> result = endpoint.collectData();
            resultController.setResult(getId(), result);
            statusController.setStatus(getId(), Status.COMPLETE);
        } catch (Exception e) {
            statusController.setStatus(getId(), Status.ERROR);
            throw new IllegalStateException(e);
        }
    }
}
