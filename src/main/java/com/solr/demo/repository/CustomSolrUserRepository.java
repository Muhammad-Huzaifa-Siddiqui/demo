package com.solr.demo.repository;

import com.solr.demo.model.SolrUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;

import javax.annotation.Resource;
import java.util.List;

public class CustomSolrUserRepository {
    @Resource
    private SolrTemplate solrTemplate;

    public List<SolrUser> dynamicSearch(String searchTerm) {
        Criteria conditions = createConditions(searchTerm);
        SimpleQuery search = new SimpleQuery(conditions);

        search.addSort(sortByIdDesc());

        Page<SolrUser> results = solrTemplate.queryForPage("SolrUser", search, SolrUser.class);
        return results.getContent();
    }

    private Criteria createConditions(String searchTerm) {
        Criteria conditions = null;

        for (String term: searchTerm.split(" ")) {
            if (conditions == null) {
                conditions = new Criteria("userId").contains(term)
                        .or(new Criteria("userName").contains(term));
            }
            else {
                conditions = conditions.or(new Criteria("solrUserid").contains(term))
                        .or(new Criteria("userName").contains(term));
            }
        }
        return conditions;
    }
    

    private Sort sortByIdDesc() {return Sort.by(Sort.Direction.DESC,"solrUserid");}
}
