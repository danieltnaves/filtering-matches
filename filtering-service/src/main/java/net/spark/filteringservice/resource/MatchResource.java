package net.spark.filteringservice.resource;

import net.spark.filteringservice.dto.PageMatchDTO;
import org.springframework.http.ResponseEntity;

public interface MatchResource {

  ResponseEntity<PageMatchDTO> filterMatchesBasedOnDetails(
      Boolean hasPhoto,
      Boolean inContact,
      Boolean favourite,
      Double compatibilityScore,
      Integer age,
      Integer height,
      Integer distanceInKm,
      int page,
      int size);
}
