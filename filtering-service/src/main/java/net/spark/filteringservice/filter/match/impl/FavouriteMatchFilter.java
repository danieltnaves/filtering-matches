package net.spark.filteringservice.filter.match.impl;

import net.spark.filteringservice.filter.match.MatchFilter;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Order(3)
public class FavouriteMatchFilter extends MatchFilter {

  private static final String FAVOURITE = "favourite";

  @Override
  protected boolean validateFilterDetails(Map<String, String> filterDetails) {
    return filterDetails != null && filterDetails.containsKey(FAVOURITE);
  }

  @Override
  protected boolean areDomainValuesInvalid(Map<String, String> filterDetails) {
    return !filterDetails.get(FAVOURITE).equals("true")
        && !filterDetails.get(FAVOURITE).equals("false");
  }

  @Override
  protected void addCriteriaToQuery(Map<String, String> filterDetails, Query query) {
    query.addCriteria(Criteria.where(FAVOURITE).is(Boolean.parseBoolean(filterDetails.get(FAVOURITE))));
  }
}
