package net.spark.filteringservice.resource;

import net.spark.filteringservice.model.Match;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MatchResource {

  ResponseEntity<List<Match>> filterMatchesBasedOnDetails();

}
