package net.spark.filteringservice.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.Map;

import lombok.extern.log4j.Log4j2;
import net.spark.filteringservice.dto.MatchDTO;
import net.spark.filteringservice.dto.PageMatchDTO;
import net.spark.filteringservice.filter.match.MatchFilterChain;
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

  private MatchFilterChain matchFilterChain;

  @Autowired
  public MatchServiceImpl(MatchRepository matchRepository, MatchFilterChain matchFilterChain) {
    this.matchRepository = matchRepository;
    this.matchFilterChain = matchFilterChain;
  }

  @Override
  public PageMatchDTO findMatchesBasedOnDetails(
      final Map<String, String> filterDetails, final int page, final int size) {

    log.info("m=findMatchesBasedOnDetails, details = {}", filterDetails);

    final Page<Match> matchesBasedOnDetails =
        matchRepository.findAll(
            matchFilterChain.filterProcessor(filterDetails), PageRequest.of(page, size));

    return PageMatchDTO.builder()
        .matches(matchesBasedOnDetails.stream().map(MatchDTO::fromMatch).collect(toList()))
        .totalMatches(matchesBasedOnDetails.getTotalElements())
        .totalMatchesPages(matchesBasedOnDetails.getTotalPages())
        .build();
  }
}
