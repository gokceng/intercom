package com.gguner.intercom.service.io;

import com.gguner.intercom.service.io.exc.ResourceNotReadException;

import java.util.Set;

/**
 * Gokcen Guner
 * 09/01/2018 00:08
 **/
public interface ResourceReader<T> {
  Set<T> readFromFile(String path, Class<T> tClass) throws ResourceNotReadException;
}
