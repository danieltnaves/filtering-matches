package net.spark.filteringservice.resource.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import net.spark.filteringservice.dto.MatchDto;
import net.spark.filteringservice.dto.MatchRequestDto;
import net.spark.filteringservice.dto.PageMatchDto;
import net.spark.filteringservice.resource.MatchResource;
import net.spark.filteringservice.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        @ApiResponse(code = 204, message = "No matches found"),
        @ApiResponse(code = 400, message = "Invalid input, object invalid")
      })
  @RequestMapping(
      value = "/match/filter",
      produces = {"application/json"},
      method = RequestMethod.GET)
  @Override
  public ResponseEntity<PageMatchDto> filterMatchesBasedOnDetails(
      @RequestParam Map<String, String> filterDetails,
      @RequestParam(value = "page", required = true) int page,
      @RequestParam(value = "size", required = true) int size) {
    log.info("m=filterMatchesBasedOnDetails, page = {}, size = {}", page, size);
    return ResponseEntity.ok()
        .body(matchService.findMatchesBasedOnDetails(filterDetails, page, size));
  }

  @ApiOperation(
      value = "Creates a new match",
      nickname = "createMatch",
      notes = "Creates a new match")
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = "New match created with success"),
        @ApiResponse(code = 400, message = "Invalid input, object invalid")
      })
  @RequestMapping(
      value = "/match",
      consumes = {"application/json"},
      produces = {"application/json"},
      method = RequestMethod.POST)
  @Override
  public ResponseEntity<Void> createMatch(@ApiParam(value = "Match to create") @Valid @RequestBody MatchRequestDto matchRequestDto)
      throws URISyntaxException {
    MatchDto newMatch = matchService.createNewMatch(matchRequestDto.toMatch());
    return ResponseEntity.created(new URI(String.format("/match/%s", newMatch.getId()))).build();
  }
}
