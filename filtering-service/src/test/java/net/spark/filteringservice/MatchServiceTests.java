package net.spark.filteringservice;

import java.util.ArrayList;
import java.util.List;

import net.spark.filteringservice.model.Match;
import net.spark.filteringservice.repository.MatchRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MatchServiceTests {

  @Mock private MatchRepository matchRepository;

  private static List<Match> matches = new ArrayList<>();

  @BeforeClass
  public static void loadData() {
    matches.add(
        new Match(
            null,
            22,
            "Berlin",
            0.8,
            5,
            "Alice",
            true,
            140.5,
            "Software Developer",
            null,
            "http://path.to/photo.jpg",
            "Hinduism"));
    matches.add(
        new Match(
            null,
            30,
            "Amsterdam",
            0.9,
            0,
            "Bob",
            true,
            150.5,
            "Doctor",
            null,
            "http://path.to/photo.jpg",
            "Christianity"));
    matches.add(
        new Match(
            null,
            19,
            "London",
            0.6,
            1,
            "Bryan",
            true,
            160.7,
            "Software Developer",
            null,
            "http://path.to/photo.jpg",
            "Hinduism"));
    matches.add(
        new Match(
            null,
            40,
            "Munich",
            0.8,
            5,
            "Rose",
            true,
            140.5,
            "Engineer",
            null,
            "http://path.to/photo.jpg",
            "Judaism"));
    matches.add(
        new Match(
            null,
            45,
            "Dublin",
            0.8,
            5,
            "Juan",
            true,
            140.5,
            "Pilot",
            null,
            "http://path.to/photo.jpg",
            "Gnosticism"));
  }

  @Test
  public void filterMatchesBasedOnDetailsPageableTest() {
    //    when(matchRepository.findMatchesBasedOnDetails(any(), any(Pageable.class)))
    //        .thenReturn(new PageImpl<>(matches));
    //    matchRepository.findMatchesBasedOnDetails(null, PageRequest.of(1, 3));
    //    MatchService matchService = new MatchServiceImpl(matchRepository);
    //    matchService.findMatchesBasedOnDetails(null, 1, 5);
  }
}
