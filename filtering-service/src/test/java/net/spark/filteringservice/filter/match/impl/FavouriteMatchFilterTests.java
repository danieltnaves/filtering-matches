package net.spark.filteringservice.filter.match.impl;

import net.spark.filteringservice.exception.BadRequestException;
import net.spark.filteringservice.filter.match.MatchFilter;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class FavouriteMatchFilterTests {

  @Test
  public void emptyFavouriteFilterTest() {
    MatchFilter favouriteMatchFilter = new FavouriteMatchFilter();
    Query query = new Query();
    favouriteMatchFilter.process(null, query);
    assertEquals("Query: { }, Fields: { }, Sort: { }", query.toString());
  }

  @Test
  public void invalidKeyFavouriteFilterTest() {
    MatchFilter favouriteMatchFilter = new FavouriteMatchFilter();
    Query query = new Query();
    favouriteMatchFilter.process(new HashMap<>(), query);
    assertEquals("Query: { }, Fields: { }, Sort: { }", query.toString());
  }

  @Test
  public void validTrueKeyFavouriteFilterTest() {
    MatchFilter favouriteMatchFilter = new FavouriteMatchFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("favourite", "true");
    favouriteMatchFilter.process(filterDetails, query);
    assertEquals("Query: { \"favourite\" : true }, Fields: { }, Sort: { }", query.toString());
  }

  @Test
  public void validFalseKeyFavouriteFilterTest() {
    MatchFilter favouriteMatchFilter = new FavouriteMatchFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("favourite", "false");
    favouriteMatchFilter.process(filterDetails, query);
    assertEquals("Query: { \"favourite\" : false }, Fields: { }, Sort: { }", query.toString());
  }

  @Test(expected = BadRequestException.class)
  public void invalidBooleanValueValidationExceptionTest() {
    MatchFilter favouriteMatchFilter = new FavouriteMatchFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("favourite", "INVALID_VALUE");
    favouriteMatchFilter.process(filterDetails, query);
  }

}
