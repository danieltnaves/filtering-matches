package net.spark.filteringservice.service;

import java.util.*;

import net.spark.filteringservice.dto.PageMatchDto;
import net.spark.filteringservice.dto.SmileDto;
import net.spark.filteringservice.exception.NotFoundException;
import net.spark.filteringservice.filter.match.MatchFilterChain;
import net.spark.filteringservice.filter.match.impl.AgeMatchFilter;
import net.spark.filteringservice.filter.match.impl.HeightMatchFilter;
import net.spark.filteringservice.model.Match;
import net.spark.filteringservice.repository.MatchRepository;
import net.spark.filteringservice.service.impl.MatchServiceImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
            130.5,
            "Pilot",
            null,
            "http://path.to/photo.jpg",
            "Gnosticism"));
  }

  @Test
  public void filterMatchesBasedOnDetailsPageableTest() {
    when(matchRepository.findAllMatchesByFilterDetails(any(), any(Pageable.class)))
        .thenReturn(new PageImpl<>(matches));

    MatchFilterChain matchFilterChain =
        new MatchFilterChain(Arrays.asList(new HeightMatchFilter(), new AgeMatchFilter()));

    MatchService matchService = new MatchServiceImpl(matchRepository, matchFilterChain);

    Map<String, String> filterDetails = new HashMap<>();
    filterDetails.put("height", "140");
    filterDetails.put("age", "40");

    PageMatchDto matchesBasedOnDetails = matchService.findMatchesBasedOnDetails(filterDetails, 1, 10);
    assertEquals("Alice", matchesBasedOnDetails.getMatches().get(0).getDisplayName());
    assertEquals("Bob", matchesBasedOnDetails.getMatches().get(1).getDisplayName());
    assertEquals("Bryan", matchesBasedOnDetails.getMatches().get(2).getDisplayName());
    assertEquals("Rose", matchesBasedOnDetails.getMatches().get(3).getDisplayName());
    assertEquals("Juan", matchesBasedOnDetails.getMatches().get(4).getDisplayName());
  }

  @Test
  public void sendSmileShouldIncreaseContactsExchangedCounterTest() {
    final Match match = new Match(
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
            "Hinduism");
    when(matchRepository.save(any())).thenReturn(match);
    when(matchRepository.findById(any())).thenReturn(Optional.of(match));
    MatchService matchService = new MatchServiceImpl(matchRepository, new MatchFilterChain(new ArrayList<>()));
    Match updatedMatch = matchService.sendSmile(new SmileDto("123"));
    assertEquals((Integer) 6, updatedMatch.getContactsExchanged());
  }

  @Test(expected = NotFoundException.class)
  public void sendSmileForUserThatDoesntExistShouldThrowNotFoundExceptionTest() {
    when(matchRepository.findById(any())).thenReturn(Optional.ofNullable(null));
    MatchService matchService = new MatchServiceImpl(matchRepository, new MatchFilterChain(new ArrayList<>()));
    matchService.sendSmile(new SmileDto("123"));
  }
}
