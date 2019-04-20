package net.spark.filteringservice.resource;

import java.net.URISyntaxException;
import java.util.Map;

import net.spark.filteringservice.dto.MatchRequestDto;
import net.spark.filteringservice.dto.PageMatchDto;
import org.springframework.http.ResponseEntity;

public interface MatchResource {

  ResponseEntity<PageMatchDto> filterMatchesBasedOnDetails(
      Map<String, String> filterDetails, int page, int size);

  ResponseEntity<Void> createMatch(MatchRequestDto matchRequestDto) throws URISyntaxException;
}
