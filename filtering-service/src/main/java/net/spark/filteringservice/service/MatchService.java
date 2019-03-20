package net.spark.filteringservice.service;

import net.spark.filteringservice.dto.FilterDetailsDTO;
import net.spark.filteringservice.dto.PageMatchDTO;

public interface MatchService {

  PageMatchDTO findMatchesBasedOnDetails(FilterDetailsDTO filterDetailsDTO, int page, int size);
}
