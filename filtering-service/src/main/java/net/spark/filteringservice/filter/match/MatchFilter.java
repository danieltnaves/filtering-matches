package net.spark.filteringservice.filter.match;

import java.util.Map;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import net.spark.filteringservice.model.QMatch;

public interface MatchFilter {

  Optional<BooleanBuilder> process(
      Map<String, String> filterDetails, QMatch qMatch, BooleanBuilder filterDetailsPredicate);
}
