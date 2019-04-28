package net.spark.filteringservice.filter.match.impl;

import net.spark.filteringservice.filter.match.MatchFilter;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Order(1)
public class HasPhotoMatchFilter extends MatchFilter {

  private static final String HAS_PHOTO = "has_photo";

  private static final String MAIN_PHOTO = "mainPhoto";

  @Override
  protected boolean validateFilterDetails(Map<String, String> filterDetails) {
    return filterDetails != null && filterDetails.containsKey(HAS_PHOTO);
  }

  @Override
  protected boolean areDomainValuesInvalid(Map<String, String> filterDetails) {
    return !filterDetails.get(HAS_PHOTO).equals("true")
        && !filterDetails.get(HAS_PHOTO).equals("false");
  }

  @Override
  protected void addCriteriaToQuery(Map<String, String> filterDetails, Query query) {
    if (Boolean.parseBoolean(filterDetails.get(HAS_PHOTO))) {
      query.addCriteria(Criteria.where(MAIN_PHOTO).exists(true).ne(null));
    } else {
      query.addCriteria(Criteria.where(MAIN_PHOTO).exists(false));
    }
  }
}
