package controller.exporter;

import model.ImageInterface;

/**
 * Represents the image exporter for .jpeg and .jpg files.
 */
public class JPEGExporter extends AbstractImageExporter {
  /**
   * Constructs an image exporter with given image and specified file path.
   *
   * @param image    the image object.
   * @param filePath the image file path.
   */
  public JPEGExporter(ImageInterface image, String filePath) {
    super(image, filePath);
  }
}
