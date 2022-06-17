package controller.exporter;

/**
 * Contains the methods for an image exporter.
 */
public interface ImageExporterInterface {

  /**
   * Handles exporting the image to the specified file path.
   *
   * @throws IllegalArgumentException if it is unable to save file.
   */
  void export() throws IllegalArgumentException;
}
