package com.solr.demo.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;


@Data
public abstract class SimplifiEntity {


    protected String uuid;


    protected Timestamp creationDate;


    protected String createdBy;

    protected Timestamp lastUpdated;


    protected String updatedBy;


    protected boolean enabled;


    protected int version;

    public SimplifiEntity() {
        createdBy = "0";
        updatedBy = "0";
        uuid = UUID.randomUUID().toString();
        enabled = true;
    }
}
