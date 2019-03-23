package net.spark.filteringservice.repository.impl;

import net.spark.filteringservice.model.Match;
import net.spark.filteringservice.repository.MatchRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;

public class MatchRepositoryImpl implements MatchRepositoryCustom {

  private MongoTemplate mongoTemplate;

  @Autowired
  public MatchRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public Page<Match> findAllMatchesByFilterDetails(Query query, Pageable pageable) {
    query.with(pageable);
    return PageableExecutionUtils.getPage(
        mongoTemplate.find(query, Match.class),
        pageable,
        () -> mongoTemplate.count(query, Match.class));
  }
}
