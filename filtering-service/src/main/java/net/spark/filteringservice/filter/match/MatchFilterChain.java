package net.spark.filteringservice.filter.match;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import net.spark.filteringservice.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MatchFilterChain {

  private List<MatchFilter> matcheFilters;

  @Autowired
  public MatchFilterChain(List<MatchFilter> matcheFilters) {
    this.matcheFilters = matcheFilters;
  }

  public Query filterProcessor(Map<String, String> filterDetails) {

    if (matcheFilters == null || matcheFilters.isEmpty()) {
      throw new BadRequestException("m=filterProcessor, There is no filter registered");
    }

    final Query query = new Query();
    matcheFilters.forEach(filter -> filter.process(filterDetails, query));
    log.info("m=filterProcessor, filterDetailsPredicate = {}", query);

    return query;
  }
}
