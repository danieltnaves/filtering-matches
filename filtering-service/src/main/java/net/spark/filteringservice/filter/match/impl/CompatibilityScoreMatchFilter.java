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

  private static final String MIN_COMPATIBILITY_SCORE = "min_compatibility_score";

  private static final String MAX_COMPATIBILITY_SCORE = "max_compatibility_score";

  private static final double MINIMUM_COMPATIBILITY_SCORE = 0.01;

  private static final double MAXIMUM_COMPATIBILITY_SCORE = 0.99;

  @Override
  protected boolean validateFilterDetails(Map<String, String> filterDetails) {
    return filterDetails != null
        && (verifyMinAndMaxLimits(filterDetails) || filterDetails.containsKey(COMPATIBILITY_SCORE));
  }

  private boolean verifyMinAndMaxLimits(Map<String, String> filterDetails) {
    return filterDetails.containsKey(MIN_COMPATIBILITY_SCORE)
        && filterDetails.containsKey(MAX_COMPATIBILITY_SCORE);
  }

  @Override
  protected boolean areDomainValuesInvalid(Map<String, String> filterDetails) {
    return verifyField(COMPATIBILITY_SCORE, filterDetails)
        || (verifyField(MIN_COMPATIBILITY_SCORE, filterDetails)
            && verifyField(MAX_COMPATIBILITY_SCORE, filterDetails));
  }

  private boolean verifyField(String field, Map<String, String> filterDetails) {
    return filterDetails.containsKey(field)
        && (Double.parseDouble(filterDetails.get(field)) < MINIMUM_COMPATIBILITY_SCORE
            || Double.parseDouble(filterDetails.get(field))
                > MAXIMUM_COMPATIBILITY_SCORE);
  }

  @Override
  protected void addCriteriaToQuery(Map<String, String> filterDetails, Query query) {
    if (filterDetails.containsKey(COMPATIBILITY_SCORE)) {
      query.addCriteria(
              Criteria.where(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, COMPATIBILITY_SCORE))
                      .gte(MINIMUM_COMPATIBILITY_SCORE)
                      .lte(Double.parseDouble(filterDetails.get(COMPATIBILITY_SCORE))));
    } else {
      query.addCriteria(
              Criteria.where(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, COMPATIBILITY_SCORE))
                      .gte(Double.parseDouble(filterDetails.get(MIN_COMPATIBILITY_SCORE)))
                      .lte(Double.parseDouble(filterDetails.get(MAX_COMPATIBILITY_SCORE))));
    }
  }
}
