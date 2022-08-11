package com.solr.demo.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact extends SimplifiEntity {


    private long id;


    private String phoneNumber;


    private String contactType;


    private User user;
}
