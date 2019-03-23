package net.spark.filteringservice.filter.match.impl;

import net.spark.filteringservice.exception.BadRequestException;
import net.spark.filteringservice.filter.match.MatchFilter;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class AgeMatchFilterTests {

  @Test
  public void emptyAgeFilterTest() {
    MatchFilter ageMatchFilter = new AgeMatchFilter();
    Query query = new Query();
    ageMatchFilter.process(null, query);
    assertEquals("Query: { }, Fields: { }, Sort: { }", query.toString());
  }

  @Test
  public void invalidKeyAgeFilterTest() {
    MatchFilter ageMatchFilter = new AgeMatchFilter();
    Query query = new Query();
    ageMatchFilter.process(new HashMap<>(), query);
    assertEquals("Query: { }, Fields: { }, Sort: { }", query.toString());
  }

  @Test
  public void validKeyAgeFilterTest() {
    MatchFilter ageMatchFilter = new AgeMatchFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("age", "30");
    ageMatchFilter.process(filterDetails, query);
    assertEquals(
        "Query: { \"age\" : { \"$gte\" : 18, \"$lte\" : 30 } }, Fields: { }, Sort: { }",
        query.toString());
  }

  @Test(expected = BadRequestException.class)
  public void minimumAgeValidationExceptionTest() {
    MatchFilter ageMatchFilter = new AgeMatchFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("age", "5");
    ageMatchFilter.process(filterDetails, query);
  }

  @Test(expected = BadRequestException.class)
  public void maximumAgeValidationExceptionTest() {
    MatchFilter ageMatchFilter = new AgeMatchFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("age", "96");
    ageMatchFilter.process(filterDetails, query);
  }
}
