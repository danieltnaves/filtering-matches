package net.spark.filteringservice.service;

import java.util.Map;

import net.spark.filteringservice.dto.PageMatchDTO;

public interface MatchService {

  PageMatchDTO findMatchesBasedOnDetails(Map<String, String> filterDetails, int page, int size);
}
