package net.spark.filteringservice.resource;

import net.spark.filteringservice.dto.PageMatchDto;
import net.spark.filteringservice.dto.SmileDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface MatchResource {

  ResponseEntity<PageMatchDto> filterMatchesBasedOnDetails(
      Map<String, String> filterDetails, int page, int size);

  ResponseEntity<Void> sendSmile(SmileDto smileDto);

}
