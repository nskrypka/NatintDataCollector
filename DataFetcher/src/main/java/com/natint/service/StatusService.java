package com.natint.service;

import com.natint.data.IData;
import com.natint.exec.ResultController;
import com.natint.exec.Status;
import com.natint.exec.StatusController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by skn on 09/10/2015.
 */
public class StatusService  {

    @Autowired
    StatusController statusController;
    @Autowired
    ResultController resultController;

    public List<IData> getResult (Integer id) {
        return resultController.getResult(id);
    }

    public Status getStatus(Integer id) {
        return statusController.getStatus(id);
    }
}
