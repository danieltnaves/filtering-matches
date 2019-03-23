package net.spark.filteringservice.filter.match.impl;

import com.google.common.base.CaseFormat;
import net.spark.filteringservice.filter.match.MatchFilter;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Order(4)
public class CompatibilityScoreMatchFilter extends MatchFilter {

  private static final String COMPATIBILITY_SCORE = "compatibility_score";

  private static final double MINIMUM_COMPATIBILITY_SCORE = 0.1;

  private static final double MAXIMUM_COMPATIBILITY_SCORE = 0.99;

  @Override
  protected boolean validateFilterDetails(Map<String, String> filterDetails) {
    return filterDetails != null && filterDetails.containsKey(COMPATIBILITY_SCORE);
  }

  @Override
  protected boolean validateDomainValuesExpression(Map<String, String> filterDetails) {
    return Double.valueOf(filterDetails.get(COMPATIBILITY_SCORE)) < MINIMUM_COMPATIBILITY_SCORE
        || Double.valueOf(filterDetails.get(COMPATIBILITY_SCORE)) > MAXIMUM_COMPATIBILITY_SCORE;
  }

  @Override
  protected void addCriteriaToQuery(Map<String, String> filterDetails, Query query) {
    query.addCriteria(
        Criteria.where(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, COMPATIBILITY_SCORE))
            .gte(MINIMUM_COMPATIBILITY_SCORE)
            .lte(Double.valueOf(filterDetails.get(COMPATIBILITY_SCORE))));
  }
}
