package net.spark.filteringservice.filter.match.impl;

import com.querydsl.core.BooleanBuilder;
import net.spark.filteringservice.dto.FilterDetailsDTO;
import net.spark.filteringservice.filter.match.MatchFilter;
import net.spark.filteringservice.model.QMatch;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Order(4)
public class CompatibilityScoreMatchFilter implements MatchFilter {
  @Override
  public Optional<BooleanBuilder> process(
      FilterDetailsDTO filterDetailsDTO, QMatch qMatch, BooleanBuilder filterDetailsPredicate) {
    return Optional.ofNullable(filterDetailsDTO.getCompatibilityScore())
        .map(compabilityScore -> qMatch.compatibilityScore.between(0.01, filterDetailsDTO.getCompatibilityScore()))
        .map(filterDetailsPredicate::and);
  }
}
