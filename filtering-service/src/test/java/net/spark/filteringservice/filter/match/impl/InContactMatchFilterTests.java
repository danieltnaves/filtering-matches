package net.spark.filteringservice.filter.match.impl;

import net.spark.filteringservice.exception.BadRequestException;
import net.spark.filteringservice.filter.match.MatchFilter;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class InContactMatchFilterTests {

  @Test
  public void emptyInContactFilterTest() {
    MatchFilter inContactMatchFilter = new InContactMatchFilter();
    Query query = new Query();
    inContactMatchFilter.process(null, query);
    assertEquals("Query: { }, Fields: { }, Sort: { }", query.toString());
  }

  @Test
  public void invalidKeyInContactFilterTest() {
    MatchFilter inContactMatchFilter = new InContactMatchFilter();
    Query query = new Query();
    inContactMatchFilter.process(new HashMap<>(), query);
    assertEquals("Query: { }, Fields: { }, Sort: { }", query.toString());
  }

  @Test
  public void validTrueKeyInContactFilterTest() {
    MatchFilter inContactMatchFilter = new InContactMatchFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("in_contact", "true");
    inContactMatchFilter.process(filterDetails, query);
    assertEquals(
        "Query: { \"contactsExchanged\" : { \"$gt\" : 0 } }, Fields: { }, Sort: { }",
        query.toString());
  }

  @Test
  public void validFalseKeyInContactFilterTest() {
    MatchFilter inContactMatchFilter = new InContactMatchFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("in_contact", "false");
    inContactMatchFilter.process(filterDetails, query);
    assertEquals("Query: { \"contactsExchanged\" : 0 }, Fields: { }, Sort: { }", query.toString());
  }

  @Test(expected = BadRequestException.class)
  public void invalidBooleanValueValidationExceptionTest() {
    MatchFilter inContactMatchFilter = new InContactMatchFilter();
    Query query = new Query();
    HashMap<String, String> filterDetails = new HashMap<>();
    filterDetails.put("in_contact", "INVALID_VALUE");
    inContactMatchFilter.process(filterDetails, query);
  }

}
