package net.spark.filteringservice.filter.match.impl;

import net.spark.filteringservice.exception.BadRequestException;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NameMatchFilterTests {

  private static final String NAME = "name";

  @Test
  public void emptyNameFilterTest() {
    NameMatchFilter nameMatchFilter = new NameMatchFilter();
    Query query = new Query();
    nameMatchFilter.process(null, query);
    assertEquals("Query: { }, Fields: { }, Sort: { }", query.toString());
  }

  @Test
  public void invalidKeyNameFilterTest() {
    NameMatchFilter nameMatchFilter = new NameMatchFilter();
    Query query = new Query();
    nameMatchFilter.process(new HashMap<>(), query);
    assertEquals("Query: { }, Fields: { }, Sort: { }", query.toString());
  }

  @Test
  public void validKeyNameFilterTest() {
    NameMatchFilter nameMatchFilter = new NameMatchFilter();
    Map<String, String> filters = new HashMap<>();
    filters.put("name", "daniel");
    assertTrue(nameMatchFilter.validateFilterDetails(filters));
  }

  @Test
  public void validKeyNameQueryTest() {
    NameMatchFilter nameMatchFilter = new NameMatchFilter();
    Map<String, String> filters = new HashMap<>();
    filters.put(NAME, "daniel");
    Query query = new Query();
    nameMatchFilter.process(filters, query);
    assertEquals(
        "Query: { \"displayName\" : { \"$regex\" : \"daniel\", \"$options\" : \"i\" } }, Fields: { }, Sort: { }",
        query.toString());
  }

  @Test(expected = BadRequestException.class)
  public void invalidDomainValueProcessTest() {
    NameMatchFilter nameMatchFilter = new NameMatchFilter();
    Map<String, String> filters = new HashMap<>();
    filters.put(NAME, "");
    nameMatchFilter.process(filters, new Query());
  }

  @Test
  public void invalidDomainValueValidationTest() {
    NameMatchFilter nameMatchFilter = new NameMatchFilter();
    Map<String, String> filters = new HashMap<>();
    filters.put(NAME, "");
    assertTrue(nameMatchFilter.validateDomainValuesExpression(filters));
  }

  @Test(expected = BadRequestException.class)
  public void invalidCaracterDomainValueTest() {
    NameMatchFilter nameMatchFilter = new NameMatchFilter();
    Map<String, String> filters = new HashMap<>();
    filters.put(NAME, "CAR@@@@");
    nameMatchFilter.process(filters, new Query());
  }

}
