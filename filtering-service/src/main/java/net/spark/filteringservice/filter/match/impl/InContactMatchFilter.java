package net.spark.filteringservice.filter.match.impl;

import net.spark.filteringservice.filter.match.MatchFilter;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Order(2)
public class InContactMatchFilter extends MatchFilter {

  private static final String IN_CONTACT = "in_contact";

  private static final String CONTACTS_EXCHANGED = "contactsExchanged";

  @Override
  protected boolean validateFilterDetails(Map<String, String> filterDetails) {
    return filterDetails != null && filterDetails.containsKey(IN_CONTACT);
  }

  @Override
  protected boolean validateDomainValuesExpression(Map<String, String> filterDetails) {
    return !filterDetails.get(IN_CONTACT).equals("true")
        && !filterDetails.get(IN_CONTACT).equals("false");
  }

  @Override
  protected void addCriteriaToQuery(Map<String, String> filterDetails, Query query) {
    if (Boolean.parseBoolean(filterDetails.get(IN_CONTACT))) {
      query.addCriteria(Criteria.where(CONTACTS_EXCHANGED).gt(0));
    } else {
      query.addCriteria(Criteria.where(CONTACTS_EXCHANGED).is(0));
    }
  }
}
