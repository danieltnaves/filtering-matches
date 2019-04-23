package net.spark.filteringservice.resource.impl;

import java.util.Map;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import net.spark.filteringservice.dto.PageMatchDto;
import net.spark.filteringservice.dto.SmileDto;
import net.spark.filteringservice.resource.MatchResource;
import net.spark.filteringservice.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
      value = "Send a smile to the user")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "If smile was sent"),
        @ApiResponse(code = 400, message = "Invalid input, object invalid"),
        @ApiResponse(code = 404, message = "The user was not found")
      })
  @RequestMapping(
      value = "/smile",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PATCH)
  public ResponseEntity<Void> sendSmile(@Valid @RequestBody SmileDto smileDto) {
    matchService.sendSmile(smileDto);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
