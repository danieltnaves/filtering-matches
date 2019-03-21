package net.spark.filteringservice.filter.match.impl;

import com.querydsl.core.BooleanBuilder;
import net.spark.filteringservice.dto.FilterDetailsDTO;
import net.spark.filteringservice.filter.match.MatchFilter;
import net.spark.filteringservice.model.QMatch;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Order(5)
public class AgeMatchFilter implements MatchFilter {
  @Override
  public Optional<BooleanBuilder> process(
      FilterDetailsDTO filterDetailsDTO, QMatch qMatch, BooleanBuilder filterDetailsPredicate) {
    return Optional.ofNullable(filterDetailsDTO.getAge())
        .map(age -> qMatch.age.between(18, filterDetailsDTO.getAge()))
        .map(filterDetailsPredicate::and);
  }
}
