package net.spark.filteringservice.filter.match.impl;

import net.spark.filteringservice.filter.match.MatchFilter;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Order(6)
public class HeightMatchFilter extends MatchFilter {

  public static final String HEIGHT = "height";

  private static final int MINIMUM_HEIGHT = 135;

  private static final int MAXIMUM_HEIGHT = 210;

  public static final String HEIGHT_IN_CM = "heightInCm";

  @Override
  protected boolean validateFilterDetails(Map<String, String> filterDetails) {
    return filterDetails != null && filterDetails.containsKey(HEIGHT);
  }

  @Override
  protected boolean validateDomainValuesExpression(Map<String, String> filterDetails) {
    return Integer.valueOf(filterDetails.get(HEIGHT)) < MINIMUM_HEIGHT
        || Integer.valueOf(filterDetails.get(HEIGHT)) > MAXIMUM_HEIGHT;
  }

  @Override
  protected void addCriteriaToQuery(Map<String, String> filterDetails, Query query) {
    query.addCriteria(
        Criteria.where(HEIGHT_IN_CM).gte(MINIMUM_HEIGHT).lte(Integer.valueOf(filterDetails.get(HEIGHT))));
  }
}
