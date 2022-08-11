package com.solr.demo.model;

import lombok.Data;

@Data

public class UserDetail extends SimplifiEntity {


    private long id;


    private String longCode;


    private String cardProgramUuid;


    private String dob;


    private String role;


    private String position;

    private User user;
}
