package org.sebastiansiarczynski.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.sebastiansiarczynski.util.Validator;

public class InMemoryCache {

  private final Map<String, Map<String, Integer>> cache;
  private static InMemoryCache instance;

  private InMemoryCache() {
    cache = new HashMap<>();
  }

  public static InMemoryCache getInstance() {
    if (instance == null) {
      instance = new InMemoryCache();
    }

    return instance;
  }

  public Map<String, Integer> getValue(final String key) {
    Validator.validateEmptyStringOrNull(key, "Key cannot be empty or null!");

    return cache.get(key);
  }

  public void setValue(final String key, final Map<String, Integer> value) {
    Validator.validateEmptyStringOrNull(key, "Key cannot be empty or null!");

    Objects.requireNonNull(value);
    cache.put(key, value);
  }

  public boolean checkIfExists(final String key) {
    Validator.validateEmptyStringOrNull(key, "Key cannot be empty or null!");

    return cache.containsKey(key);
  }
}
