package org.sebastiansiarczynski.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StringUtil {

  public Map<String, Integer> mostPopularWord(final String text) {
    Validator.validateEmptyStringOrNull(text, "Text cannot be empty or null!");

    final Map<String, Integer> words = new HashMap<>();

    Arrays.stream(text.split(" ")).forEach(word -> {
      words.computeIfPresent(word, (key, value) -> value + 1);
      words.putIfAbsent(word, 1);
    });

    final List<Entry<String, Integer>> sorted = words.entrySet().stream()
        .sorted(Comparator.comparingInt(Entry::getValue)).toList();

    final Entry<String, Integer> first = sorted.get(sorted.size() - 1);

    return Map.ofEntries(first);
  }
}
