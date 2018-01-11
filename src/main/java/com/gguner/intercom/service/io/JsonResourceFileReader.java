package com.gguner.intercom.service.io;

import com.gguner.intercom.service.io.exc.ResourceFormatException;
import com.gguner.intercom.service.io.exc.ResourceNotReadException;
import com.gguner.intercom.service.serdes.exc.DeserializationException;
import com.gguner.intercom.service.serdes.json.JsonSerDes;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Gokcen Guner
 * 09/01/2018 00:11
 **/
public class JsonResourceFileReader<T> implements ResourceReader<T> {
  private final ClassLoader classLoader;
  private final JsonSerDes jsonSerDes;

  public JsonResourceFileReader(JsonSerDes jsonSerDes) {
    this.classLoader = getClass().getClassLoader();
    this.jsonSerDes = jsonSerDes;
  }

  @Override
  public Set<T> readFromFile(String path, Class<T> tClass) throws ResourceNotReadException {
    Set<String> lines = readLines(path);
    return deserializeLines(lines, tClass);
  }

  private Set<String> readLines(String path) throws ResourceNotReadException {
    try (Stream<String> lines = Files.lines(Paths.get(classLoader.getResource(path).toURI()))) {
      return lines.collect(Collectors.toSet());
    } catch (NullPointerException | IOException | URISyntaxException e) {
      throw new ResourceNotReadException("Cannot read the source", e);
    }
  }

  private Set<T> deserializeLines(Set<String> lines, Class<T> tClass) throws ResourceFormatException {
    Set<T> tSet = new HashSet<>();
    for (String line : lines) {
      T t = deserialize(line, tClass);
      tSet.add(t);
    }
    return tSet;
  }

  private T deserialize(String line, Class<T> tClass) throws ResourceFormatException {
    try {
      return jsonSerDes.deserialize(line, tClass);
    } catch (DeserializationException e) {
      throw new ResourceFormatException("Cannot read the source", e);
    }
  }
}
