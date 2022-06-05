package com.thbono.restaurants.domain.repository;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class CSVRepository<T> {

  protected abstract String getDataFile();

  protected abstract T buildEntity(final String[] line);

  public List<T> findAll() {
    var dataFile = getDataFile();

    try {
      var inputStream = getClass().getClassLoader().getResourceAsStream((dataFile));
      var inputReader = new InputStreamReader(Objects.requireNonNull(inputStream));
      var reader = new CSVReader(inputReader);

      reader.skip(1);
      var rows = reader.readAll();
      reader.close();

      return rows.stream().map(this::buildEntity).collect(Collectors.toList());
    } catch (CsvException | IOException ex) {
      throw new RuntimeException(String.format("Failed to read data file: %s", dataFile), ex);
    }
  }
}
