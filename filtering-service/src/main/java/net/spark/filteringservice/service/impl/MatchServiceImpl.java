package net.spark.filteringservice.service.impl;

import static java.util.stream.Collectors.toList;

import lombok.extern.log4j.Log4j2;
import net.spark.filteringservice.converter.FilterDetailsConverter;
import net.spark.filteringservice.dto.FilterDetailsDTO;
import net.spark.filteringservice.dto.MatchDTO;
import net.spark.filteringservice.dto.PageMatchDTO;
import net.spark.filteringservice.model.Match;
import net.spark.filteringservice.repository.MatchRepository;
import net.spark.filteringservice.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MatchServiceImpl implements MatchService {

  private MatchRepository matchRepository;

  private FilterDetailsConverter filterDetailsConverter;

  @Autowired
  public MatchServiceImpl(MatchRepository matchRepository, FilterDetailsConverter filterDetailsConverter) {
    this.matchRepository = matchRepository;
    this.filterDetailsConverter = filterDetailsConverter;
  }

  @Override
  public PageMatchDTO findMatchesBasedOnDetails(
      final FilterDetailsDTO filterDetailsDTO, final int page, final int size) {

    log.info("m=findMatchesBasedOnDetails, details = {}", filterDetailsDTO);

    final Page<Match> matchesBasedOnDetails =
        matchRepository.findAll(
            filterDetailsConverter.buildPredicateFromFilterDetailsDTO(filterDetailsDTO),
            PageRequest.of(page, size));

    return PageMatchDTO.builder()
        .matches(matchesBasedOnDetails.stream().map(MatchDTO::fromMatch).collect(toList()))
        .totalMatches(matchesBasedOnDetails.getTotalElements())
        .totalMatchesPages(matchesBasedOnDetails.getTotalPages())
        .build();
  }
}
