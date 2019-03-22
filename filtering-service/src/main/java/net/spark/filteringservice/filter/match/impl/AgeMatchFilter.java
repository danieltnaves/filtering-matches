package net.spark.filteringservice.filter.match.impl;

import java.util.Map;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import net.spark.filteringservice.filter.match.MatchFilter;
import net.spark.filteringservice.model.QMatch;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class AgeMatchFilter implements MatchFilter {

  public static final String AGE = "age";

  @Override
  public Optional<BooleanBuilder> process(
      final Map<String, String> filterDetails,
      final QMatch qMatch,
      final BooleanBuilder filterDetailsPredicate) {
    return Optional.ofNullable(filterDetails.get(AGE))
        .map(age -> qMatch.age.between(18, Integer.valueOf(age)))
        .map(filterDetailsPredicate::and);
  }
}
