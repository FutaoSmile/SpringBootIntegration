package com.futao.springmvcdemo.model.entity;

import java.sql.Timestamp;

/**
 * @author futao
 * Created on 2018-12-21.
 */
public class SystemInformation {
    private Timestamp startTimestamp;

    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Timestamp startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public SystemInformation(Timestamp startTimestamp) {
        this.startTimestamp = startTimestamp;
    }
}
