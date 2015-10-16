package com.natint.task;

import com.natint.email.EmailFactory;
import com.natint.exec.Status;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by skn on 12/10/2015.
 */
public class EmailTask extends Task{

    @Autowired
    EmailFactory emailFactory;

    public EmailTask() {
        super();
    }

    @Override
    public void init(Map<String, String> params) {
        taskStatus.setStatus(getId(), Status.INITIALIZED);
        this.params = params;
        this.endpoint = emailFactory.getInstance(params.get("name"));
    }

}
