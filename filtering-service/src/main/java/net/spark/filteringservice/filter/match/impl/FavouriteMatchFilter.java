package net.spark.filteringservice.filter.match.impl;

import java.util.Map;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import net.spark.filteringservice.filter.match.MatchFilter;
import net.spark.filteringservice.model.QMatch;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class FavouriteMatchFilter implements MatchFilter {

  public static final String FAVOURITE = "favourite";

  @Override
  public Optional<BooleanBuilder> process(
      final Map<String, String> filterDetails,
      final QMatch qMatch,
      final BooleanBuilder filterDetailsPredicate) {
    return Optional.ofNullable(filterDetails.get(FAVOURITE))
        .map(favourite -> qMatch.favourite.eq(Boolean.valueOf(favourite)))
        .map(filterDetailsPredicate::and);
  }
}
