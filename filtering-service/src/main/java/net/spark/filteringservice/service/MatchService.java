package net.spark.filteringservice.service;

import java.util.Map;

import net.spark.filteringservice.dto.PageMatchDto;

public interface MatchService {

  PageMatchDto findMatchesBasedOnDetails(Map<String, String> filterDetails, int page, int size);
}
