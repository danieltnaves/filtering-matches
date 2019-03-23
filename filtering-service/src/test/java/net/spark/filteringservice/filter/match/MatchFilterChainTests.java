//package net.spark.filteringservice.filter.match;
//
//import net.spark.filteringservice.filter.match.impl.AgeMatchFilter;
//import net.spark.filteringservice.filter.match.impl.FavouriteMatchFilter;
//import net.spark.filteringservice.filter.match.impl.HasPhotoMatchFilter;
//import org.junit.Test;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.Assert.assertEquals;
//
//public class MatchFilterChainTests {
//
//  @Test
//  public void createFilterChainTest() {
//
//    MatchFilterChain matchFilterChain =
//        new MatchFilterChain(
//            Arrays.asList(
//                new HasPhotoMatchFilter(), new FavouriteMatchFilter(), new AgeMatchFilter()));
//
//    Map<String, String> filterDetails = new HashMap<>();
//    filterDetails.put("has_photo", "true");
//    filterDetails.put("age", "30");
//
//    assertEquals(
//        "!empty(match.mainPhoto) && match.age between 18 and 30",
//        matchFilterChain.filterProcessor(filterDetails).toString());
//  }
//
//  @Test
//  public void createEmptyFilterChainTest() {
////    MatchFilterChain matchFilterChain = new MatchFilterChain(null);
////    assertNull(matchFilterChain.filterProcessor(null));
//  }
//}
