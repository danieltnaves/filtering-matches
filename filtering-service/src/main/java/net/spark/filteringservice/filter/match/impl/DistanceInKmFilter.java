package net.spark.filteringservice.filter.match.impl;

import net.spark.filteringservice.filter.match.MatchFilter;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Order(7)
public class DistanceInKmFilter extends MatchFilter {

  private static final String DISTANCE_IN_KM = "distance_in_km";

  private static final String LOCATION = "location";

  private static final String LONGITUDE = "longitude";

  private static final String LATITUDE = "latitude";

  private static final double METERS_PER_KILOMETER = 1000;

  private static final int MINIMUM_DISTANCE_IN_KM = 30;

  private static final int MAXIMUM_DISTANCE_IN_KM = 300;

  @Override
  protected boolean validateFilterDetails(Map<String, String> filterDetails) {
    return filterDetails != null
        && filterDetails.containsKey(DISTANCE_IN_KM)
        && filterDetails.containsKey(LONGITUDE)
        && filterDetails.containsKey(LATITUDE);
  }

  @Override
  protected boolean validateDomainValuesExpression(Map<String, String> filterDetails) {
    return Integer.valueOf(filterDetails.get(DISTANCE_IN_KM)) < MINIMUM_DISTANCE_IN_KM
        || Integer.valueOf(filterDetails.get(DISTANCE_IN_KM)) > MAXIMUM_DISTANCE_IN_KM;
  }

  @Override
  protected void addCriteriaToQuery(Map<String, String> filterDetails, Query query) {
    query.addCriteria(
        Criteria.where(LOCATION)
            .nearSphere(getGeoJsonPoint(filterDetails))
            .maxDistance(
                Integer.valueOf(filterDetails.get(DISTANCE_IN_KM)) * METERS_PER_KILOMETER));
  }

  private GeoJsonPoint getGeoJsonPoint(Map<String, String> filterDetails) {
    return new GeoJsonPoint(
        Double.valueOf(filterDetails.get(LONGITUDE)), Double.valueOf(filterDetails.get(LATITUDE)));
  }
}
