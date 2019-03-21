package net.spark.filteringservice.filter.match;

import com.querydsl.core.BooleanBuilder;
import net.spark.filteringservice.dto.FilterDetailsDTO;
import net.spark.filteringservice.model.QMatch;

import java.util.Optional;

public interface MatchFilter {

  Optional<BooleanBuilder> process(
      FilterDetailsDTO filterDetailsDTO, QMatch qMatch, BooleanBuilder filterDetailsPredicate);
}
