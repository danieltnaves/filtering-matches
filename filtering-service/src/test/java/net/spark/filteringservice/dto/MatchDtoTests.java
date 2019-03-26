package net.spark.filteringservice.dto;

import net.spark.filteringservice.model.Match;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MatchDtoTests {

  @Test
  public void fromMatchConversionTest() {
    MatchDto matchDto =
        MatchDto.fromMatch(
            new Match(
                null,
                20,
                "Uberlandia",
                0.5,
                5,
                "Daniel",
                true,
                178.0,
                "Engineer",
                null,
                "http://photo",
                "Religion"));
    assertEquals(Integer.valueOf(20), matchDto.getAge());
    assertEquals("Uberlandia", matchDto.getCityName());
    assertEquals(new Double(0.5), matchDto.getCompatibilityScore());
    assertEquals("Daniel", matchDto.getDisplayName());
    assertTrue(matchDto.getFavourite());
    assertEquals(new Double(178.0), matchDto.getHeightInCm());
    assertEquals("Engineer", matchDto.getJobTitle());
    assertEquals("http://photo", matchDto.getMainPhoto());
    assertEquals("Religion", matchDto.getReligion());
  }
}
