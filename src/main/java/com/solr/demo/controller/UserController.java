package com.solr.demo.controller;

import com.solr.demo.model.SolrUser;
import com.solr.demo.model.User;
import com.solr.demo.repository.SolrUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class UserController {
    @Autowired
    SolrUserRepository solrUserRepository;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/user")
    public String createSolrUser(@RequestBody SolrUser user){
        String description = "User Created";
        solrUserRepository.save(user);
        return description;
    }

    @PostMapping("/sync")
    public List<String> syncUserToSolr(){
        ResponseEntity<List<User>> response= restTemplate.exchange("http://localhost:8081/v1/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                });
        List<User> users = response.getBody();
        List<String> userNames = new ArrayList<>();
        List<SolrUser> solrUsers = new ArrayList<>();
        users.forEach(user1 -> {
            SolrUser solrUser = new SolrUser();
            solrUser.setKycStatus(user1.getKycStatus());
            solrUser.setUserStatus(user1.getUserStatus());
            solrUser.setUserName(user1.getUserName());
            solrUser.setMiddleName(user1.getMiddleName());
            solrUser.setGender(user1.getGender());
            solrUser.setSolrUserid(user1.getId());
            solrUser.setEmail(user1.getEmail());
            solrUser.setLastName(user1.getLastName());
            solrUser.setFirstName(user1.getFirstName());
            solrUser.setCompanyUuid("123");
//            solrUser.setPhoneNumber(user1.getContacts().iterator().next().getPhoneNumber() == null ? "0010" : user1.getContacts().iterator().next().getPhoneNumber() );
            solrUsers.add(solrUser);
            userNames.add(user1.getUserName());
        });
        solrUserRepository.saveAll(solrUsers);
        return userNames;
    }

    @GetMapping("/user/{userid}")
    public SolrUser readSolrUser(@PathVariable Long userid){
        return solrUserRepository.findBySolrUserid(userid);
    }

    @PutMapping("/user")
    public String updateSolrUser(@RequestBody SolrUser user){
        String description = "SolrUser Updated";
        solrUserRepository.save(user);
        return description;
    }

    @DeleteMapping("/user/{userid}")
    public String deleteSolrUser(@PathVariable Long userid){
        String description = "SolrUser Deleted";
        solrUserRepository.delete(solrUserRepository.findBySolrUserid(userid));
        return description;
    }

    @GetMapping("/user/desc/{userDesc}/{page}")
    public List<SolrUser> findSolrUser(@PathVariable String userDesc, @PathVariable int page){
        return solrUserRepository.findBySolrUserName(userDesc, PageRequest.of(page, 2)).getContent();
    }

    @GetMapping("/user/search/{searchTerm}")
    public List<SolrUser> findSolrUserBySearchTerm(@PathVariable String searchTerm){
        return solrUserRepository.findByCustomQuery(searchTerm);
    }

}
