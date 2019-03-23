package net.spark.filteringservice.filter.match;

import net.spark.filteringservice.exception.BadRequestException;
import net.spark.filteringservice.filter.match.impl.AgeMatchFilter;
import net.spark.filteringservice.filter.match.impl.FavouriteMatchFilter;
import net.spark.filteringservice.filter.match.impl.HasPhotoMatchFilter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MatchFilterChainTests {

  @Test
  public void createFilterChainTest() {

    MatchFilterChain matchFilterChain =
        new MatchFilterChain(
            Arrays.asList(
                new HasPhotoMatchFilter(), new FavouriteMatchFilter(), new AgeMatchFilter()));

    Map<String, String> filterDetails = new HashMap<>();
    filterDetails.put("has_photo", "true");
    filterDetails.put("age", "30");

    assertEquals(
        "Query: { \"mainPhoto\" : { \"$exists\" : true, \"$ne\" : null }, \"age\" : { \"$gte\" : 18, \"$lte\" : 30 } }, Fields: { }, Sort: { }",
        matchFilterChain.filterProcessor(filterDetails).toString());
  }

  @Test(expected = BadRequestException.class)
  public void createNullFilterChainTest() {
    MatchFilterChain matchFilterChain = new MatchFilterChain(null);
    matchFilterChain.filterProcessor(null);
  }

  @Test(expected = BadRequestException.class)
  public void createEmptyFilterChainTest() {
    MatchFilterChain matchFilterChain = new MatchFilterChain(new ArrayList<>());
    matchFilterChain.filterProcessor(null);
  }
}
