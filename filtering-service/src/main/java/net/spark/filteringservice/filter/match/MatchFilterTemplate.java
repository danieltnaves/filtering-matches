package net.spark.filteringservice.filter.match;

import net.spark.filteringservice.exception.BadRequestException;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Map;

public abstract class MatchFilterTemplate {

  void process(Map<String, String> filterDetails, Query query) {
    if (validateFilterDetails(filterDetails)) {
      validateDomainValues(filterDetails);
      addCriteriaToQuery(filterDetails, query);
    }
  }

  protected abstract boolean validateFilterDetails(Map<String, String> filterDetails);

  private void validateDomainValues(Map<String, String> filterDetails) {
    if (validateDomainValuesExpression(filterDetails)) {
      throw new BadRequestException("Invalid filter value");
    }
  }

  protected abstract boolean validateDomainValuesExpression(Map<String, String> filterDetails);

  protected abstract void addCriteriaToQuery(Map<String, String> filterDetails, Query query);
}
