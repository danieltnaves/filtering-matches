package net.spark.filteringservice.filter.match.impl;

import net.spark.filteringservice.filter.match.MatchFilter;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Order(5)
public class AgeMatchFilter extends MatchFilter {

  private static final String AGE = "age";

  private static final int MINIMUM_AGE = 18;

  private static final int MAXIMUM_AGE = 95;

  @Override
  protected boolean validateDomainValuesExpression(Map<String, String> filterDetails) {
    return Integer.parseInt(filterDetails.get(AGE)) < MINIMUM_AGE
        || Integer.parseInt(filterDetails.get(AGE)) > MAXIMUM_AGE;
  }

  @Override
  protected boolean validateFilterDetails(Map<String, String> filterDetails) {
    return filterDetails != null && filterDetails.containsKey(AGE);
  }

  @Override
  protected void addCriteriaToQuery(Map<String, String> filterDetails, Query query) {
    query.addCriteria(
        Criteria.where(AGE).gte(MINIMUM_AGE).lte(Integer.valueOf(filterDetails.get(AGE))));
  }
}
