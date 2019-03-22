package net.spark.filteringservice.filter.match.impl;

import java.util.Map;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import net.spark.filteringservice.filter.match.MatchFilter;
import net.spark.filteringservice.model.QMatch;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class CompatibilityScoreMatchFilter implements MatchFilter {

  public static final String COMPATIBILITY_SCORE = "compatibility_score";

  @Override
  public Optional<BooleanBuilder> process(
      Map<String, String> filterDetails, QMatch qMatch, BooleanBuilder filterDetailsPredicate) {
    return Optional.ofNullable(filterDetails.get(COMPATIBILITY_SCORE))
        .map(
            compabilityScore ->
                qMatch.compatibilityScore.between(0.01, Double.valueOf(compabilityScore)))
        .map(filterDetailsPredicate::and);
  }
}
