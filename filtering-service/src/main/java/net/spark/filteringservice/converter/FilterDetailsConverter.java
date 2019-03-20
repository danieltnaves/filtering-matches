package net.spark.filteringservice.converter;

import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import net.spark.filteringservice.dto.FilterDetailsDTO;
import net.spark.filteringservice.model.QMatch;
import org.springframework.stereotype.Component;

@Component
public class FilterDetailsConverter {

  public Predicate buildPredicateFromFilterDetailsDTO(final FilterDetailsDTO filterDetailsDTO) {

    final QMatch qMatch = QMatch.match;
    final BooleanBuilder filterDetailsPredicate = new BooleanBuilder();

    Optional.ofNullable(filterDetailsDTO.getHasPhoto())
        .map(hasPhoto -> hasPhoto ? qMatch.mainPhoto.isNotEmpty() : qMatch.mainPhoto.isEmpty())
        .map(filterDetailsPredicate::and);

    Optional.ofNullable(filterDetailsDTO.getInContact())
        .map(
            inContact ->
                inContact ? qMatch.contactsExchanged.gt(0) : qMatch.contactsExchanged.eq(0))
        .map(filterDetailsPredicate::and);

    Optional.ofNullable(filterDetailsDTO.getFavourite())
        .map(qMatch.favourite::eq)
        .map(filterDetailsPredicate::and);

    Optional.ofNullable(filterDetailsDTO.getCompatibilityScore())
        .map(
            compabilityScore ->
                qMatch.compatibilityScore.between(0.01, filterDetailsDTO.getCompatibilityScore()))
        .map(filterDetailsPredicate::and);

    Optional.ofNullable(filterDetailsDTO.getAge())
        .map(age -> qMatch.age.between(18, filterDetailsDTO.getAge()))
        .map(filterDetailsPredicate::and);

    Optional.ofNullable(filterDetailsDTO.getHeight())
        .map(height -> qMatch.heightInCm.between(135, filterDetailsDTO.getHeight()))
        .map(filterDetailsPredicate::and);

    return filterDetailsPredicate;
  }
}
