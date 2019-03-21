package net.spark.filteringservice.converter;

import com.querydsl.core.types.Predicate;
import lombok.extern.log4j.Log4j2;
import net.spark.filteringservice.dto.FilterDetailsDTO;
import net.spark.filteringservice.filter.match.MatchFilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class FilterDetailsConverter {

  private MatchFilterChain matchFilterChain;

  @Autowired
  public FilterDetailsConverter(MatchFilterChain matchFilterChain) {
    this.matchFilterChain = matchFilterChain;
  }

  public Predicate buildPredicateFromFilterDetailsDTO(final FilterDetailsDTO filterDetailsDTO) {
    log.info("m=buildPredicateFromFilterDetailsDTO, details = {}", filterDetailsDTO);
    return matchFilterChain.filterProcessor(filterDetailsDTO);
  }

}
