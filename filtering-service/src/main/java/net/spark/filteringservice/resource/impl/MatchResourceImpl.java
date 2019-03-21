package net.spark.filteringservice.resource.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import net.spark.filteringservice.dto.FilterDetailsDTO;
import net.spark.filteringservice.dto.PageMatchDTO;
import net.spark.filteringservice.resource.MatchResource;
import net.spark.filteringservice.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class MatchResourceImpl implements MatchResource {

  private MatchService matchService;

  @Autowired
  public MatchResourceImpl(MatchService matchService) {
    this.matchService = matchService;
  }

  @ApiOperation(
      value =
          "MatchFilter matches based on following details: Has photo, In contact, Favourite, Compatibility Score, Age, Height and Distance in KM",
      nickname = "filterMatchesBasedOnDetails",
      notes = "MatchFilter matches based on details")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Found a list of matches successfully"),
        @ApiResponse(code = 400, message = "Invalid input, object invalid"),
        @ApiResponse(code = 204, message = "No matches found")
      })
  @RequestMapping(
      value = "/match/filter",
      produces = {"application/json"},
      method = RequestMethod.GET)
  @Override
  public ResponseEntity<PageMatchDTO> filterMatchesBasedOnDetails(
      @RequestParam(value = "has_photo", required = false) Boolean hasPhoto,
      @RequestParam(value = "in_contact", required = false) Boolean inContact,
      @RequestParam(value = "favourite", required = false) Boolean favourite,
      @RequestParam(value = "compatibility_score", required = false) Double compatibilityScore,
      @RequestParam(value = "age", required = false) Integer age,
      @RequestParam(value = "height", required = false) Integer height,
      @RequestParam(value = "distance_in_km", required = false) Integer distanceInKm,
      @RequestParam(value = "page", required = true) int page,
      @RequestParam(value = "size", required = true) int size) {
    log.info("m=filterMatchesBasedOnDetails, page = {}, size = {}", page, size);
    return ResponseEntity.ok()
        .body(
            matchService.findMatchesBasedOnDetails(
                FilterDetailsDTO.builder()
                    .hasPhoto(hasPhoto)
                    .inContact(inContact)
                    .favourite(favourite)
                    .compatibilityScore(compatibilityScore)
                    .age(age)
                    .height(height)
                    .distanceInKm(distanceInKm)
                    .build(),
                page,
                size));
  }

}
