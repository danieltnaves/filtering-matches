package net.spark.filteringservice.filter.match;

import com.querydsl.core.BooleanBuilder;
import lombok.extern.slf4j.Slf4j;
import net.spark.filteringservice.dto.FilterDetailsDTO;
import net.spark.filteringservice.model.QMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MatchFilterChain {

  private List<MatchFilter> matchesMatchFilters;

  @Autowired
  public MatchFilterChain(List<MatchFilter> matchesMatchFilters) {
    this.matchesMatchFilters = matchesMatchFilters;
  }

  public BooleanBuilder filterProcessor(FilterDetailsDTO filterDetailsDTO) {
    final QMatch qMatch = QMatch.match;
    final BooleanBuilder filterDetailsPredicate = new BooleanBuilder();
    matchesMatchFilters.forEach(filter -> filter.process(filterDetailsDTO, qMatch, filterDetailsPredicate));
    log.info("m=filterProcessor, filterDetailsPredicate = {}", filterDetailsPredicate);
    return filterDetailsPredicate;
  }
}
