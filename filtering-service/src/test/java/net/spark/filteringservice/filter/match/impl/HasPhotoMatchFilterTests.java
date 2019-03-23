package net.spark.filteringservice.filter.match.impl;

import net.spark.filteringservice.exception.BadRequestException;
import net.spark.filteringservice.filter.match.MatchFilter;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class HasPhotoMatchFilterTests {

  @Test
  public void emptyHasPhotoFilterTest() {
    MatchFilter hasPhotoMatchFilter = new HasPhotoMatchFilter();
    Query query = new Query();
    hasPhotoMatchFilter.process(null, query);
    assertEquals("Query: { }, Fields: { }, Sort: { }", query.toString());
  }

  @Test
  public void invalidKeyHasPhotoFilterTest() {
    MatchFilter hasPhotoMatchFilter = new HasPhotoMatchFilter();
    Query query = new Query();
    hasPhotoMatchFilter.process(new HashMap<>(), query);
    assertEquals("Query: { }, Fields: { }, Sort: { }", query.toString());
  }

  @Test
  public void validTrueKeyHasPhotoFilterTest() {
    MatchFilter hasPhotoMatchFilter = new HasPhotoMatchFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("has_photo", "true");
    hasPhotoMatchFilter.process(filterDetails, query);
    assertEquals(
        "Query: { \"mainPhoto\" : { \"$exists\" : true, \"$ne\" : null } }, Fields: { }, Sort: { }",
        query.toString());
  }

  @Test
  public void validFalseKeyHasPhotoFilterTest() {
    MatchFilter hasPhotoMatchFilter = new HasPhotoMatchFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("has_photo", "false");
    hasPhotoMatchFilter.process(filterDetails, query);
    assertEquals(
        "Query: { \"mainPhoto\" : { \"$exists\" : false } }, Fields: { }, Sort: { }",
        query.toString());
  }

  @Test(expected = BadRequestException.class)
  public void invalidBooleanValueValidationExceptionTest() {
    MatchFilter hasPhotoMatchFilter = new HasPhotoMatchFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("has_photo", "INVALID_VALUE");
    hasPhotoMatchFilter.process(filterDetails, query);
  }

}
