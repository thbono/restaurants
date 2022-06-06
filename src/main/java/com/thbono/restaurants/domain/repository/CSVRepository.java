package com.thbono.restaurants.domain.repository;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class CSVRepository<T> {

  private final String dataFile;
  private final boolean hasHeader;

  protected CSVRepository(String dataFile, boolean hasHeader) {
    this.dataFile = dataFile;
    this.hasHeader = hasHeader;
  }

  protected abstract T buildEntity(final String[] row);

  public List<T> findByCriteria(final Predicate<T> predicate) {
    try {
      var inputStream = getClass().getClassLoader().getResourceAsStream((dataFile));
      var inputReader = new InputStreamReader(Objects.requireNonNull(inputStream));

      try (var reader = new CSVReader(inputReader)) {
        reader.skip(hasHeader ? 1 : 0);
        return StreamSupport.stream(reader.spliterator(), false)
            .map(this::buildEntity)
            .filter(predicate)
            .collect(Collectors.toList());
      }
    } catch (IOException ex) {
      throw new RuntimeException(String.format("Failed to read data file: %s", dataFile), ex);
    }
  }

  public List<T> findAll() {
    return findByCriteria(entity -> true);
  }
}
