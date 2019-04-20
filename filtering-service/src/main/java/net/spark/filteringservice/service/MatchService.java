package net.spark.filteringservice.service;

import java.util.Map;

import net.spark.filteringservice.dto.MatchDto;
import net.spark.filteringservice.dto.PageMatchDto;
import net.spark.filteringservice.model.Match;

public interface MatchService {

  PageMatchDto findMatchesBasedOnDetails(Map<String, String> filterDetails, int page, int size);

  MatchDto createNewMatch(Match match);
}
