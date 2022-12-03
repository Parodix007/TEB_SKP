package org.sebastiansiarczynski.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.sebastiansiarczynski.cache.InMemoryCache;

class StringUtilWithCacheTest {

  @Mock
  private InMemoryCache inMemoryCache;

  private StringUtilWithCache stringUtilWithCache;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    stringUtilWithCache = new StringUtilWithCache(inMemoryCache);
  }

  @AfterEach
  void tearDown() {
    Mockito.clearAllCaches();
  }

  @Test
  void mostPopularWordWithCache() {
    //given
    final String testText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut sed "
        + "pretium eros. In eu tortor quis odio mollis interdum id a nisl. Vivamus vel risus at nunc efficitur lobortis in in nulla. Mauris imperdiet purus sit amet leo interdum, quis bibendum est consequat. Phasellus et molestie magna. Quisque fringilla, urna sed finibus ornare, mi dolor gravida ante, a finibus ligula arcu at felis. Proin ac efficitur libero. Praesent a turpis ut magna imperdiet hendrerit. Mauris varius, ligula non imperdiet cursus, dui nibh cursus quam, eu tincidunt augue quam eu sem. Fusce ut arcu porta, tincidunt velit a, sollicitudin est. Donec sed diam non velit congue lacinia ac id eros. Sed tristique enim a elit iaculis, eget convallis nibh porttitor. Mauris maximus mi est, vel vehicula elit ullamcorper a. Morbi pharetra iaculis ante, et scelerisque massa maximus id. Nam consectetur vitae diam id efficitur. Mauris placerat, enim nec pharetra scelerisque, purus nibh tempus risus, egestas imperdiet ligula libero vitae nulla.";
    Mockito.when(inMemoryCache.checkIfExists(Mockito.anyString())).thenReturn(false);
    Mockito.doNothing().when(inMemoryCache).setValue(Mockito.anyString(), Mockito.anyMap());

    //when
    final Map<String, Integer> stringIntegerMap =
        stringUtilWithCache.mostPopularWordWithCache(testText);

    //then
    Mockito.verify(inMemoryCache, Mockito.times(1)).setValue(Mockito.anyString(), Mockito.anyMap());
    Mockito.verify(inMemoryCache, Mockito.times(1)).checkIfExists(Mockito.anyString());
    assertTrue(stringIntegerMap.containsKey("Mauris"));
    assertEquals(stringIntegerMap.get("Mauris"), 4);
  }

  @Test
  void getValueFromCache() {
    //given
    final String testText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut sed "
        + "pretium eros. In eu tortor quis odio mollis interdum id a nisl. Vivamus vel risus at nunc efficitur lobortis in in nulla. Mauris imperdiet purus sit amet leo interdum, quis bibendum est consequat. Phasellus et molestie magna. Quisque fringilla, urna sed finibus ornare, mi dolor gravida ante, a finibus ligula arcu at felis. Proin ac efficitur libero. Praesent a turpis ut magna imperdiet hendrerit. Mauris varius, ligula non imperdiet cursus, dui nibh cursus quam, eu tincidunt augue quam eu sem. Fusce ut arcu porta, tincidunt velit a, sollicitudin est. Donec sed diam non velit congue lacinia ac id eros. Sed tristique enim a elit iaculis, eget convallis nibh porttitor. Mauris maximus mi est, vel vehicula elit ullamcorper a. Morbi pharetra iaculis ante, et scelerisque massa maximus id. Nam consectetur vitae diam id efficitur. Mauris placerat, enim nec pharetra scelerisque, purus nibh tempus risus, egestas imperdiet ligula libero vitae nulla.";
    Mockito.when(inMemoryCache.checkIfExists(Mockito.anyString())).thenReturn(true);
    Mockito.when(inMemoryCache.getValue(Mockito.anyString())).thenReturn(Map.of("Lorem", 4));

    //when
    final Map<String, Integer> stringIntegerMap =
        stringUtilWithCache.mostPopularWordWithCache(testText);

    //then
    Mockito.verify(inMemoryCache, Mockito.times(1)).checkIfExists(Mockito.anyString());
    Mockito.verify(inMemoryCache, Mockito.times(1)).getValue(Mockito.anyString());
    assertTrue(stringIntegerMap.containsKey("Lorem"));
    assertEquals(stringIntegerMap.get("Lorem"), 4);
  }

  @Test
  void throwsWhenEmptyText() {
    assertThrows(IllegalArgumentException.class,
        () -> stringUtilWithCache.mostPopularWordWithCache(""));
  }

  @Test
  void throwsWhenNull() {
    assertThrows(IllegalArgumentException.class,
        () -> stringUtilWithCache.mostPopularWordWithCache(null));
  }

  @Test
  void throwsWhenMockThrow() {
    //given
    final String testText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut sed "
        + "pretium eros. In eu tortor quis odio mollis interdum id a nisl. Vivamus vel risus at nunc efficitur lobortis in in nulla. Mauris imperdiet purus sit amet leo interdum, quis bibendum est consequat. Phasellus et molestie magna. Quisque fringilla, urna sed finibus ornare, mi dolor gravida ante, a finibus ligula arcu at felis. Proin ac efficitur libero. Praesent a turpis ut magna imperdiet hendrerit. Mauris varius, ligula non imperdiet cursus, dui nibh cursus quam, eu tincidunt augue quam eu sem. Fusce ut arcu porta, tincidunt velit a, sollicitudin est. Donec sed diam non velit congue lacinia ac id eros. Sed tristique enim a elit iaculis, eget convallis nibh porttitor. Mauris maximus mi est, vel vehicula elit ullamcorper a. Morbi pharetra iaculis ante, et scelerisque massa maximus id. Nam consectetur vitae diam id efficitur. Mauris placerat, enim nec pharetra scelerisque, purus nibh tempus risus, egestas imperdiet ligula libero vitae nulla.";
    Mockito.when(inMemoryCache.checkIfExists(Mockito.anyString())).thenThrow();

    //then
    assertThrows(Exception.class, () -> stringUtilWithCache.mostPopularWordWithCache(testText));
  }
}