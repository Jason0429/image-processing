package controller.exporter;

import java.io.*;

/**
 * Represents the interface that exports {@code ImageInterface} objects to image files.
 */
public interface ImageExporter {
  /**
   * Exports the {@code ImageInterface} to a formatted file to a specific file path destination.
   *
   * @throws IllegalArgumentException if image file cannot be saved successfully.
   */
  void export() throws IllegalArgumentException;
}

