package net.spark.filteringservice.repository;

import net.spark.filteringservice.model.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

public interface MatchRepositoryCustom {

    Page<Match> findAllMatchesByFilterDetails(Query query, Pageable pageable);
}
