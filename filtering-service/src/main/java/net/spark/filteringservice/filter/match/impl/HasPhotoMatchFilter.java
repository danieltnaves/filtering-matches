package net.spark.filteringservice.filter.match.impl;

import java.util.Map;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import net.spark.filteringservice.filter.match.MatchFilter;
import net.spark.filteringservice.model.QMatch;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class HasPhotoMatchFilter implements MatchFilter {

  public static final String HAS_PHOTO = "has_photo";

  @Override
  public Optional<BooleanBuilder> process(
      final Map<String, String> filterDetails,
      final QMatch qMatch,
      final BooleanBuilder filterDetailsPredicate) {
    return Optional.ofNullable(filterDetails.get(HAS_PHOTO))
        .map(
            hasPhoto ->
                Boolean.valueOf(hasPhoto)
                    ? qMatch.mainPhoto.isNotEmpty()
                    : qMatch.mainPhoto.isEmpty())
        .map(filterDetailsPredicate::and);
  }
}
