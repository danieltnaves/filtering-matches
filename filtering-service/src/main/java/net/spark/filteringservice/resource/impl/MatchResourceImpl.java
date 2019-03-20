package net.spark.filteringservice.resource.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.spark.filteringservice.model.Match;
import net.spark.filteringservice.resource.MatchResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchResourceImpl implements MatchResource {

  @ApiOperation(
      value =
          "Filter matches based on following details: Has photo, In contact, Favourite, Compatibility Score, Age, Height and Distance in KM",
      nickname = "filterMatchesBasedOnDetails",
      notes = "Filter matches based on details")
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
  public ResponseEntity<List<Match>> filterMatchesBasedOnDetails() {
    return null;
  }

}
