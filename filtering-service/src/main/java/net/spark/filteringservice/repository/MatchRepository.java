package net.spark.filteringservice.repository;

import net.spark.filteringservice.model.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository
    extends MongoRepository<Match, String>, QuerydslPredicateExecutor<Match> {}
