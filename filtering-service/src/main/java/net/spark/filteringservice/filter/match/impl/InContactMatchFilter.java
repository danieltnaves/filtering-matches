package net.spark.filteringservice.filter.match.impl;

import java.util.Map;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import net.spark.filteringservice.filter.match.MatchFilter;
import net.spark.filteringservice.model.QMatch;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class InContactMatchFilter implements MatchFilter {

  public static final String IN_CONTACT = "in_contact";

  @Override
  public Optional<BooleanBuilder> process(
      final Map<String, String> filterDetails,
      final QMatch qMatch,
      final BooleanBuilder filterDetailsPredicate) {
    return Optional.ofNullable(filterDetails.get(IN_CONTACT))
        .map(
            inContact ->
                Boolean.valueOf(inContact)
                    ? qMatch.contactsExchanged.gt(0)
                    : qMatch.contactsExchanged.eq(0))
        .map(filterDetailsPredicate::and);
  }
}
