package com.solr.demo.model;

import lombok.Data;

import java.util.Set;

@Data
public class User extends SimplifiEntity {

    private long id;

    private String companyUuid;

    private String userName;

    private String gender;

    private String firstName;

    private String middleName;

    private String lastName;

    private String userStatus;

    private String kycStatus;

    private String email;

    private Set<Contact> contacts;
}
