package net.spark.filteringservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "matches")
public class Match {

  @Id private String id;

  private Integer age;

  private String cityName;

  private Double compatibilityScore;

  private Integer contactsExchanged;

  private String displayName;

  private Boolean favourite;

  private Double heightInCm;

  private String jobTitle;

  @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
  private GeoJson location;

  private String mainPhoto;

  private String religion;
}
