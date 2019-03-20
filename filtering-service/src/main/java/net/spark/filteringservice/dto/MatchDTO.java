package net.spark.filteringservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.spark.filteringservice.model.Match;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {

  private Integer age;

  private String cityName;

  private Double compatibilityScore;

  private Integer contactsExchanged;

  private String displayName;

  private Boolean favourite;

  private Double heightInCm;

  private String jobTitle;

  private String mainPhoto;

  private String religion;

  public static MatchDTO fromMatch(final Match match) {
    return MatchDTO.builder()
        .age(match.getAge())
        .cityName(match.getCityName())
        .compatibilityScore(match.getCompatibilityScore())
        .contactsExchanged(match.getContactsExchanged())
        .displayName(match.getDisplayName())
        .favourite(match.getFavourite())
        .heightInCm(match.getHeightInCm())
        .jobTitle(match.getJobTitle())
        .mainPhoto(match.getMainPhoto())
        .religion(match.getReligion())
        .build();
  }
}
