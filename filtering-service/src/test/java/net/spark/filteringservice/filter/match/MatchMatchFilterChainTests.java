package net.spark.filteringservice.filter.match;

import net.spark.filteringservice.dto.FilterDetailsDTO;
import org.junit.Test;

public class MatchMatchFilterChainTests {

  @Test
  public void createFilterChainTest() {
    MatchFilterChain matchFilterChain = new MatchFilterChain(null);
    matchFilterChain.filterProcessor(new FilterDetailsDTO(true, true, true, 0.8, 10, 135, 50));
  }
}
