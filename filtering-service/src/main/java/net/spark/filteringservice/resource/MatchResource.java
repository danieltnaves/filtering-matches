package net.spark.filteringservice.resource;

import java.util.Map;

import net.spark.filteringservice.dto.PageMatchDTO;
import org.springframework.http.ResponseEntity;

public interface MatchResource {

  ResponseEntity<PageMatchDTO> filterMatchesBasedOnDetails(
      Map<String, String> filterDetails, int page, int size);
}
