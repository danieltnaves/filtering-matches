package net.spark.filteringservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.spark.filteringservice.model.Match;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MatchRequestDto")
public class MatchRequestDto {

  @NotNull @ApiModelProperty private Integer age;

  @NotNull @ApiModelProperty private String cityName;

  @NotNull @ApiModelProperty private Double compatibilityScore;

  @NotNull @ApiModelProperty private Integer contactsExchanged;

  @NotNull @ApiModelProperty private String displayName;

  @NotNull @ApiModelProperty private Boolean favourite;

  @NotNull @ApiModelProperty private Double heightInCm;

  @ApiModelProperty private String jobTitle;

  @ApiModelProperty private String mainPhoto;

  @NotNull @ApiModelProperty private String religion;

  public Match toMatch() {
    return Match.builder()
        .age(age)
        .cityName(cityName)
        .compatibilityScore(compatibilityScore)
        .contactsExchanged(contactsExchanged)
        .displayName(displayName)
        .favourite(favourite)
        .heightInCm(heightInCm)
        .jobTitle(jobTitle)
        .mainPhoto(mainPhoto)
        .religion(religion)
        .build();
  }
}
