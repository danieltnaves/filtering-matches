package net.spark.filteringservice.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.Map;
import java.util.Optional;

import lombok.extern.log4j.Log4j2;
import net.spark.filteringservice.dto.MatchDto;
import net.spark.filteringservice.dto.PageMatchDto;
import net.spark.filteringservice.exception.BadRequestException;
import net.spark.filteringservice.exception.NoContentException;
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
  public PageMatchDto findMatchesBasedOnDetails(
      final Map<String, String> filterDetails, final int page, final int size) {

    log.info("m=findMatchesBasedOnDetails, details = {}", filterDetails);

    final Page<Match> matchesBasedOnDetails =
        matchRepository.findAllMatchesByFilterDetails(
            matchFilterChain.filterProcessor(filterDetails), PageRequest.of(page, size));

    if (matchesBasedOnDetails == null || matchesBasedOnDetails.isEmpty()) {
      throw new NoContentException("No matches found");
    }

    return PageMatchDto.builder()
        .matches(matchesBasedOnDetails.stream().map(MatchDto::fromMatch).collect(toList()))
        .totalMatches(matchesBasedOnDetails.getTotalElements())
        .totalMatchesPages(matchesBasedOnDetails.getTotalPages())
        .build();
  }

  @Override
  public MatchDto createNewMatch(Match match) {
    log.info("m=createNewMatch, Creating a new match, match = {}", match);
    if (match == null) {
      throw new BadRequestException("Invalid match input");
    }
    return Optional.ofNullable(matchRepository.save(match))
        .map(MatchDto::fromMatch)
        .orElseThrow(() -> new BadRequestException("Failed to save a new match"));
  }
}
