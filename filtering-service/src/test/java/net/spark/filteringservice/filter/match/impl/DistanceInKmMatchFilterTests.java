package net.spark.filteringservice.filter.match.impl;

import net.spark.filteringservice.exception.BadRequestException;
import net.spark.filteringservice.filter.match.MatchFilter;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class DistanceInKmMatchFilterTests {

  @Test
  public void emptyDistanceInKmFilterTest() {
    MatchFilter distanceInKmFilter = new DistanceInKmFilter();
    Query query = new Query();
    distanceInKmFilter.process(null, query);
    assertEquals("Query: { }, Fields: { }, Sort: { }", query.toString());
  }

  @Test
  public void invalidKeyDistanceInKmFilterTest() {
    MatchFilter distanceInKmFilter = new DistanceInKmFilter();
    Query query = new Query();
    distanceInKmFilter.process(new HashMap<>(), query);
    assertEquals("Query: { }, Fields: { }, Sort: { }", query.toString());
  }

  @Test
  public void validDistanceInKmFilterTest() {
    MatchFilter distanceInKmFilter = new DistanceInKmFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("distance_in_km", "30");
    filterDetails.put("longitude", "-0.118092");
    filterDetails.put("latitude", "51.509865");
    distanceInKmFilter.process(filterDetails, query);
    assertEquals(
        "Query: { \"location\" : { \"$nearSphere\" : { \"$geometry\" : { \"$java\" : " +
                "Point [x=-0.118092, y=51.509865] }, \"$maxDistance\" : 30000.0 } } }, " +
                "Fields: { }, Sort: { }",
        query.toString());
  }

  @Test(expected = BadRequestException.class)
  public void minimumDistanceInKmValidationExceptionTest() {
    MatchFilter distanceInKmFilter = new DistanceInKmFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("distance_in_km", "29");
    filterDetails.put("longitude", "-0.118092");
    filterDetails.put("latitude", "51.509865");
    distanceInKmFilter.process(filterDetails, query);
  }

  @Test(expected = BadRequestException.class)
  public void maximumDistanceInKmValidationExceptionTest() {
    MatchFilter distanceInKmFilter = new DistanceInKmFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("distance_in_km", "301");
    filterDetails.put("longitude", "-0.118092");
    filterDetails.put("latitude", "51.509865");
    distanceInKmFilter.process(filterDetails, query);
  }
}
