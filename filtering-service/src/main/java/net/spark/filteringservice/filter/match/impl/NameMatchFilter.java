package net.spark.filteringservice.filter.match.impl;

import net.spark.filteringservice.filter.match.MatchFilter;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Order(8)
public class NameMatchFilter extends MatchFilter {

  private static final String DISPLAY_NAME = "displayName";

  private static final String NAME = "name";

  @Override
  protected boolean validateFilterDetails(Map<String, String> filterDetails) {
    return filterDetails != null && filterDetails.containsKey(NAME);
  }

  @Override
  protected boolean validateDomainValuesExpression(Map<String, String> filterDetails) {
    // ~, #, @, *, +, %, {, }, <, >, [, ], |, “, ”, \, _, ^
    return filterDetails.get(NAME).isEmpty()
        || filterDetails.get(NAME).split("[~#@*+%{}<>\\[\\]|\"\\_^]", 2).length > 1;
  }

  @Override
  protected void addCriteriaToQuery(Map<String, String> filterDetails, Query query) {
    query.addCriteria(Criteria.where(DISPLAY_NAME).regex(filterDetails.get(NAME), "i"));
  }
}
