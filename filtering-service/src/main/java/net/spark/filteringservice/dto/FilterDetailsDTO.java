package net.spark.filteringservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.MultiValueMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterDetailsDTO {

  private Boolean hasPhoto;

  private Boolean inContact;

  private Boolean favourite;

  private Double compatibilityScore;

  private Integer age;

  private Integer height;

  private Integer distanceInKm;

}
