package net.spark.filteringservice.resource;

import lombok.extern.slf4j.Slf4j;
import net.spark.filteringservice.FilteringServiceApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = FilteringServiceApplication.class)
@Slf4j
public class MatchResourceIT {

  private static final String MATCH_FILTER_API = "/match/filter";

  private static final String TOTAL_MATCHES = "$.totalMatches";

  private static final String TOTAL_MATCHES_PAGES = "$.totalMatchesPages";

  private static final String PHOTO_URL = "http://thecatapi.com/api/images/get?format=src&type=gif";

  private static final String PAGE = "page";

  private static final String SIZE = "size";

  private static final String LONDON = "London";

  private static final String HARLOW = "Harlow";

  @Autowired private WebApplicationContext webApplicationContext;

  private MockMvc mvc;

  @Before
  public void beforeEachCallSetUp() {
    this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  public void filterResultsWithoutPageAndSizeTest() throws Exception {
    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    log.info("m=filterResultsWithoutPageAndSizeTest, filter = {}", params);
    mvc.perform(get(MATCH_FILTER_API).params(params)).andExpect(status().isBadRequest());
  }

  @Test
  public void resultWithNoContentTest() throws Exception {
    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add(PAGE, "0");
    params.add(SIZE, "100");
    params.add("age", "19");
    log.info("m=resultWithNoContentTest, filter = {}", params);
    mvc.perform(get(MATCH_FILTER_API).params(params)).andExpect(status().isNoContent());
  }

  @Test
  public void filterResultsWithPageAndSizeTest() throws Exception {
    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add(PAGE, "0");
    params.add(SIZE, "100");
    log.info("m=filterResultsWithPageAndSizeTest, filter = {}", params);
    mvc.perform(get(MATCH_FILTER_API).params(params))
        .andExpect(status().isOk())
        .andExpect(jsonPath(TOTAL_MATCHES, is(25)))
        .andExpect(jsonPath(TOTAL_MATCHES_PAGES, is(1)));
  }

  @Test
  public void filterHeightTest() throws Exception {
    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add(PAGE, "0");
    params.add(SIZE, "100");
    params.add("height", "150");
    log.info("m=filterHeightTest, filter = {}", params);
    mvc.perform(get(MATCH_FILTER_API).params(params))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.matches[0].heightInCm", is(144.0)))
        .andExpect(jsonPath("$.matches[1].heightInCm", is(140.0)))
        .andExpect(jsonPath("$.matches[2].heightInCm", is(148.0)))
        .andExpect(jsonPath("$.matches[3].heightInCm", is(144.0)))
        .andExpect(jsonPath("$.matches[4].heightInCm", is(145.0)))
        .andExpect(jsonPath("$.matches[5].heightInCm", is(150.0)))
        .andExpect(jsonPath("$.matches[6].heightInCm", is(144.0)))
        .andExpect(jsonPath(TOTAL_MATCHES, is(7)))
        .andExpect(jsonPath(TOTAL_MATCHES_PAGES, is(1)));
  }

  @Test
  public void filterAgeTest() throws Exception {
    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add(PAGE, "0");
    params.add(SIZE, "100");
    params.add("age", "30");
    log.info("m=filterAgeTest, filter = {}", params);
    mvc.perform(get(MATCH_FILTER_API).params(params))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.matches[0].age", is(25)))
        .andExpect(jsonPath(TOTAL_MATCHES, is(1)))
        .andExpect(jsonPath(TOTAL_MATCHES_PAGES, is(1)));
  }

  @Test
  public void filterCompatibilityScoreTest() throws Exception {
    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add(PAGE, "0");
    params.add(SIZE, "100");
    params.add("compatibility_score", "0.5");
    log.info("m=filterCompatibilityScoreTest, filter = {}", params);
    mvc.perform(get(MATCH_FILTER_API).params(params))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.matches[0].compatibilityScore", is(0.47)))
        .andExpect(jsonPath("$.matches[1].compatibilityScore", is(0.5)))
        .andExpect(jsonPath(TOTAL_MATCHES, is(2)))
        .andExpect(jsonPath(TOTAL_MATCHES_PAGES, is(1)));
  }

  @Test
  public void filterDistanceInKmTest() throws Exception {
    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add(PAGE, "0");
    params.add(SIZE, "100");
    params.add("distance_in_km", "50");
    params.add("longitude", "-0.118092");
    params.add("latitude", "51.509865");
    log.info("m=filterCompatibilityScoreTest, filter = {}", params);
    mvc.perform(get(MATCH_FILTER_API).params(params))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.matches[0].cityName", is(LONDON)))
        .andExpect(jsonPath("$.matches[1].cityName", is(LONDON)))
        .andExpect(jsonPath("$.matches[2].cityName", is(LONDON)))
        .andExpect(jsonPath("$.matches[3].cityName", is(LONDON)))
        .andExpect(jsonPath("$.matches[4].cityName", is(LONDON)))
        .andExpect(jsonPath("$.matches[5].cityName", is(LONDON)))
        .andExpect(jsonPath("$.matches[6].cityName", is(HARLOW)))
        .andExpect(jsonPath(TOTAL_MATCHES, is(7)))
        .andExpect(jsonPath(TOTAL_MATCHES_PAGES, is(1)));
  }

  @Test
  public void filterFavouriteTest() throws Exception {
    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add(PAGE, "0");
    params.add(SIZE, "100");
    params.add("favourite", "true");
    log.info("m=filterFavouriteTest, filter = {}", params);
    mvc.perform(get(MATCH_FILTER_API).params(params))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.matches[0].favourite", is(true)))
            .andExpect(jsonPath("$.matches[1].favourite", is(true)))
            .andExpect(jsonPath("$.matches[2].favourite", is(true)))
            .andExpect(jsonPath("$.matches[3].favourite", is(true)))
            .andExpect(jsonPath("$.matches[4].favourite", is(true)))
            .andExpect(jsonPath("$.matches[5].favourite", is(true)))
            .andExpect(jsonPath(TOTAL_MATCHES, is(6)))
            .andExpect(jsonPath(TOTAL_MATCHES_PAGES, is(1)));
  }

  @Test
  public void filterHasPhotoTest() throws Exception {
    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add(PAGE, "0");
    params.add(SIZE, "3");
    params.add("has_photo", "true");
    log.info("m=filterHasPhotoTest, filter = {}", params);
    mvc.perform(get(MATCH_FILTER_API).params(params))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.matches[0].mainPhoto", is(PHOTO_URL)))
            .andExpect(jsonPath("$.matches[1].mainPhoto", is(PHOTO_URL)))
            .andExpect(jsonPath("$.matches[2].mainPhoto", is(PHOTO_URL)))
            .andExpect(jsonPath(TOTAL_MATCHES, is(22)))
            .andExpect(jsonPath(TOTAL_MATCHES_PAGES, is(8)));
  }

  @Test
  public void filterInContactTest() throws Exception {
    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add(PAGE, "0");
    params.add(SIZE, "3");
    params.add("in_contact", "true");
    log.info("m=filterInContactTest, filter = {}", params);
    mvc.perform(get(MATCH_FILTER_API).params(params))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.matches[0].contactsExchanged", is(2)))
            .andExpect(jsonPath("$.matches[1].contactsExchanged", is(5)))
            .andExpect(jsonPath("$.matches[2].contactsExchanged", is(4)))
            .andExpect(jsonPath(TOTAL_MATCHES, is(12)))
            .andExpect(jsonPath(TOTAL_MATCHES_PAGES, is(4)));
  }

  @Test
  public void verifyAllFieldsTest() throws Exception {
    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add(PAGE, "0");
    params.add(SIZE, "1");
    log.info("m=verifyAllFieldsTest, filter = {}", params);
    mvc.perform(get(MATCH_FILTER_API).params(params))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.matches[0].age", is(41)))
            .andExpect(jsonPath("$.matches[0].cityName", is("Leeds")))
            .andExpect(jsonPath("$.matches[0].compatibilityScore", is(0.76)))
            .andExpect(jsonPath("$.matches[0].contactsExchanged", is(2)))
            .andExpect(jsonPath("$.matches[0].displayName", is("Caroline")))
            .andExpect(jsonPath("$.matches[0].favourite", is(true)))
            .andExpect(jsonPath("$.matches[0].heightInCm", is(153.0)))
            .andExpect(jsonPath("$.matches[0].jobTitle", is("Corporate Lawyer")))
            .andExpect(jsonPath("$.matches[0].mainPhoto", is(PHOTO_URL)))
            .andExpect(jsonPath("$.matches[0].religion", is("Atheist")))
            .andExpect(jsonPath(TOTAL_MATCHES, is(25)))
            .andExpect(jsonPath(TOTAL_MATCHES_PAGES, is(25)));
  }

  @Test
  public void pagedResultsTest() throws Exception {
    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add(PAGE, "0");
    params.add(SIZE, "20");
    log.info("m=pagedResultsTest, filter = {}", params);
    mvc.perform(get(MATCH_FILTER_API).params(params))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.matches[0].displayName", is("Caroline")))
            .andExpect(jsonPath(TOTAL_MATCHES, is(25)))
            .andExpect(jsonPath(TOTAL_MATCHES_PAGES, is(2)));
    params.set(PAGE, "1");
    log.info("m=pagedResultsTest, filter = {}", params);
    mvc.perform(get(MATCH_FILTER_API).params(params))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.matches[0].displayName", is("Kysha")))
            .andExpect(jsonPath(TOTAL_MATCHES, is(25)))
            .andExpect(jsonPath(TOTAL_MATCHES_PAGES, is(2)));
  }

  @Test
  public void filterWithMoreThanOneParameterTest() throws Exception {
    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add(PAGE, "0");
    params.add(SIZE, "100");
    params.add("in_contact", "true");
    params.add("has_photo", "true");
    params.add("height", "150");
    log.info("m=filterWithMoreThanOneParameterTest, filter = {}", params);
    mvc.perform(get(MATCH_FILTER_API).params(params))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.matches[0].displayName", is("Natalia")))
            .andExpect(jsonPath("$.matches[1].displayName", is("Clare")))
            .andExpect(jsonPath("$.matches[2].displayName", is("Elizabeth")))
            .andExpect(jsonPath(TOTAL_MATCHES, is(3)))
            .andExpect(jsonPath(TOTAL_MATCHES_PAGES, is(1)));
  }


}
