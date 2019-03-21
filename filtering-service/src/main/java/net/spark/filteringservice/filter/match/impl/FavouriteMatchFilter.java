package net.spark.filteringservice.filter.match.impl;

import com.querydsl.core.BooleanBuilder;
import net.spark.filteringservice.dto.FilterDetailsDTO;
import net.spark.filteringservice.filter.match.MatchFilter;
import net.spark.filteringservice.model.QMatch;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Order(3)
public class FavouriteMatchFilter implements MatchFilter {
  @Override
  public Optional<BooleanBuilder> process(
      FilterDetailsDTO filterDetailsDTO, QMatch qMatch, BooleanBuilder filterDetailsPredicate) {
    return Optional.ofNullable(filterDetailsDTO.getFavourite())
        .map(qMatch.favourite::eq)
        .map(filterDetailsPredicate::and);
  }
}
