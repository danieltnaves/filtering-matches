package net.spark.filteringservice.filter.match.impl;

import net.spark.filteringservice.exception.BadRequestException;
import net.spark.filteringservice.filter.match.MatchFilter;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class HeightMatchFilterTests {

  @Test
  public void emptyHeightFilterTest() {
    MatchFilter heightMatchFilter = new HeightMatchFilter();
    Query query = new Query();
    heightMatchFilter.process(null, query);
    assertEquals("Query: { }, Fields: { }, Sort: { }", query.toString());
  }

  @Test
  public void invalidKeyHeightFilterTest() {
    MatchFilter heightMatchFilter = new HeightMatchFilter();
    Query query = new Query();
    heightMatchFilter.process(new HashMap<>(), query);
    assertEquals("Query: { }, Fields: { }, Sort: { }", query.toString());
  }

  @Test
  public void validHeightFilterTest() {
    MatchFilter heightMatchFilter = new HeightMatchFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("height", "150");
    heightMatchFilter.process(filterDetails, query);
    assertEquals(
        "Query: { \"height\" : { \"$gte\" : 135, \"$lte\" : 150 } }, Fields: { }, Sort: { }",
        query.toString());
  }

  @Test(expected = BadRequestException.class)
  public void minimumHeightValidationExceptionTest() {
    MatchFilter heightMatchFilter = new HeightMatchFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("height", "134");
    heightMatchFilter.process(filterDetails, query);
  }

  @Test(expected = BadRequestException.class)
  public void maximumHeightInKmValidationExceptionTest() {
    MatchFilter heightMatchFilter = new HeightMatchFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("height", "211");
    heightMatchFilter.process(filterDetails, query);
  }
}
