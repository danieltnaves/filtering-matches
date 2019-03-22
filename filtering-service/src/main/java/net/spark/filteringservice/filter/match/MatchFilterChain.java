package net.spark.filteringservice.filter.match;

import java.util.List;
import java.util.Map;

import com.querydsl.core.BooleanBuilder;
import lombok.extern.slf4j.Slf4j;
import net.spark.filteringservice.model.QMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MatchFilterChain {

  private List<MatchFilter> matchesMatchFilters;

  @Autowired
  public MatchFilterChain(List<MatchFilter> matchesMatchFilters) {
    this.matchesMatchFilters = matchesMatchFilters;
  }

  public BooleanBuilder filterProcessor(Map<String, String> filterDetails) {
    final QMatch qMatch = QMatch.match;
    final BooleanBuilder filterDetailsPredicate = new BooleanBuilder();
    matchesMatchFilters.forEach(
        filter -> filter.process(filterDetails, qMatch, filterDetailsPredicate));
    log.info("m=filterProcessor, filterDetailsPredicate = {}", filterDetailsPredicate);
    return filterDetailsPredicate;
  }
}
