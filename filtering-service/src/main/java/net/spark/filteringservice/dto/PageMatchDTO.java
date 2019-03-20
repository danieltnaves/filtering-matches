package net.spark.filteringservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageMatchDTO {

  private List<MatchDTO> matches;

  private long totalMatches;

  private int totalMatchesPages;
}
