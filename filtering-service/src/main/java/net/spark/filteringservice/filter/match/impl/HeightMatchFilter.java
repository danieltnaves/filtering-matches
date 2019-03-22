package net.spark.filteringservice.filter.match.impl;

import java.util.Map;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import net.spark.filteringservice.filter.match.MatchFilter;
import net.spark.filteringservice.model.QMatch;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(6)
public class HeightMatchFilter implements MatchFilter {

  public static final String HEIGHT = "height";

  @Override
  public Optional<BooleanBuilder> process(
      final Map<String, String> filterDetails,
      final QMatch qMatch,
      final BooleanBuilder filterDetailsPredicate) {
    return Optional.ofNullable(filterDetails.get(HEIGHT))
        .map(height -> qMatch.heightInCm.between(135, Integer.valueOf(height)))
        .map(filterDetailsPredicate::and);
  }
}
