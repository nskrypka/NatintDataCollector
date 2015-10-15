package com.natint.exec;

/**
 * Created by skn on 08/10/2015.
 */
public enum Status {
    INITIALIZED("Initialized"), INPROGRESS("In Progress"), COMPLETE("Complete"), NOTFOUND("Not found"), ERROR("Error");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
