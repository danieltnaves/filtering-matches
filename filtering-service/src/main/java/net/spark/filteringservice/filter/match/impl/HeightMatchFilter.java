package net.spark.filteringservice.filter.match.impl;

import net.spark.filteringservice.filter.match.MatchFilterTemplate;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Order(6)
public class HeightMatchFilter extends MatchFilterTemplate {

  public static final String HEIGHT = "height";

  private static final int MINIMUM_HEIGHT = 135;

  private static final int MAXIMUM_HEIGHT = 210;

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
        Criteria.where(HEIGHT).gte(MINIMUM_HEIGHT).lte(Integer.valueOf(filterDetails.get(HEIGHT))));
  }
}
