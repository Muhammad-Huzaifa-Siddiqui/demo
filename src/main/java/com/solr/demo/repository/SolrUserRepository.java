package com.solr.demo.repository;

import com.solr.demo.model.SolrUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface SolrUserRepository  extends SolrCrudRepository<SolrUser, Long> {
    SolrUser findBySolrUserid(Long solrUserid);

    @Query("userName:*?0*")
    Page<SolrUser> findBySolrUserName(String searchTerm, Pageable pageable);

    @Query("firstName:*?0* OR middleName:*?0* OR lastName:*?0* OR userName:*?0*")
    List<SolrUser> findByCustomQuery(String searchTerm);

}
