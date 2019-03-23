package net.spark.filteringservice.filter.match.impl;

import net.spark.filteringservice.exception.BadRequestException;
import net.spark.filteringservice.filter.match.MatchFilter;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class CompatibilityScoreMatchFilterTests {

  @Test
  public void emptyCompatibilityScoreFilterTest() {
    MatchFilter compatibilityScoreMatchFilter = new CompatibilityScoreMatchFilter();
    Query query = new Query();
    compatibilityScoreMatchFilter.process(null, query);
    assertEquals("Query: { }, Fields: { }, Sort: { }", query.toString());
  }

  @Test
  public void invalidKeyCompatibilityScoreFilterTest() {
    MatchFilter compatibilityScoreMatchFilter = new CompatibilityScoreMatchFilter();
    Query query = new Query();
    compatibilityScoreMatchFilter.process(new HashMap<>(), query);
    assertEquals("Query: { }, Fields: { }, Sort: { }", query.toString());
  }

  @Test
  public void validKeyCompatibilityScoreFilterTest() {
    MatchFilter compatibilityScoreMatchFilter = new CompatibilityScoreMatchFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("compatibility_score", "0.7");
    compatibilityScoreMatchFilter.process(filterDetails, query);
    assertEquals(
        "Query: { \"compatibilityScore\" : { \"$gte\" : 0.1, \"$lte\" : 0.7 } }, Fields: { }, Sort: { }",
        query.toString());
  }

  @Test(expected = BadRequestException.class)
  public void minimumCompatibilityScoreValidationExceptionTest() {
    MatchFilter compatibilityScoreMatchFilter = new CompatibilityScoreMatchFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("compatibility_score", "-1");
    compatibilityScoreMatchFilter.process(filterDetails, query);
  }

  @Test(expected = BadRequestException.class)
  public void maximumCompatibilityScoreValidationExceptionTest() {
    MatchFilter compatibilityScoreMatchFilter = new CompatibilityScoreMatchFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("compatibility_score", "1.0");
    compatibilityScoreMatchFilter.process(filterDetails, query);
  }
}
