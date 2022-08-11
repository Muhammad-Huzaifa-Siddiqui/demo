package com.solr.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;


@Data
@SolrDocument(collection="user")
public class SolrUser {
    @Id
    @Indexed(name = "solrUserid", type = "long")
    private Long solrUserid;

    @Indexed(name = "companyUuid", type = "String")
    private String companyUuid;

    @Indexed(name = "userName", type = "String")
    private String userName;

    @Indexed(name = "gender", type = "String")
    private String gender;

    @Indexed(name = "firstName", type = "String")
    private String firstName;

    @Indexed(name = "middleName", type = "String")
    private String middleName;

    @Indexed(name = "lastName", type = "String")
    private String lastName;

    @Indexed(name = "userStatus", type = "String")
    private String userStatus;

    @Indexed(name = "kycStatus", type = "String")
    private String kycStatus;

    @Indexed(name = "email", type = "String")
    private String email;

//    @Indexed(name = "phoneNumber", type = "String")
//    private String phoneNumber;

}
