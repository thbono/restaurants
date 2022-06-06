package com.thbono.restaurants.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class TestUtil {

  private TestUtil() {}

  private static InputStream loadResource(String path) {
    return TestUtil.class.getClassLoader().getResourceAsStream((path));
  }

  public static <T> List<T> loadResourceList(String path, Class<T> clazz) {
    try {
      var mapper = new ObjectMapper();
      return mapper.readValue(loadResource(path), mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    } catch (IOException ex) {
      throw new RuntimeException("Failed to load JSON", ex);
    }
  }
}
