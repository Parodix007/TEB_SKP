package org.sebastiansiarczynski.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class StringUtilTest {

  private final StringUtil stringUtil = new StringUtil();

  @AfterEach
  void tearDown() {
    Mockito.clearAllCaches();
  }

  @Test
  void mostPopularWord() {
    //given
    final String testText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut sed "
        + "pretium eros. In eu tortor quis odio mollis interdum id a nisl. Vivamus vel risus at nunc efficitur lobortis in in nulla. Mauris imperdiet purus sit amet leo interdum, quis bibendum est consequat. Phasellus et molestie magna. Quisque fringilla, urna sed finibus ornare, mi dolor gravida ante, a finibus ligula arcu at felis. Proin ac efficitur libero. Praesent a turpis ut magna imperdiet hendrerit. Mauris varius, ligula non imperdiet cursus, dui nibh cursus quam, eu tincidunt augue quam eu sem. Fusce ut arcu porta, tincidunt velit a, sollicitudin est. Donec sed diam non velit congue lacinia ac id eros. Sed tristique enim a elit iaculis, eget convallis nibh porttitor. Mauris maximus mi est, vel vehicula elit ullamcorper a. Morbi pharetra iaculis ante, et scelerisque massa maximus id. Nam consectetur vitae diam id efficitur. Mauris placerat, enim nec pharetra scelerisque, purus nibh tempus risus, egestas imperdiet ligula libero vitae nulla.";

    //when
    final Map<String, Integer> stringIntegerMap = stringUtil.mostPopularWord(testText);

    //then
    assertTrue(stringIntegerMap.containsKey("Mauris"));
    assertEquals(stringIntegerMap.get("Mauris"), 4);
  }

  @Test
  void mostPopularWordSingle() {
    //given
    final String testText = "Lorem";

    //when
    final Map<String, Integer> most = stringUtil.mostPopularWord(testText);

    //then
    assertEquals(most.size(), 1);
    assertTrue(most.containsKey("Lorem"));
  }

  @Test
  void throwsWhenEmptyText() {
    assertThrows(IllegalArgumentException.class, () -> stringUtil.mostPopularWord(""));
  }

  @Test
  void throwsWhenNull() {
    assertThrows(IllegalArgumentException.class, () -> stringUtil.mostPopularWord(null));
  }
}