package controller.exporter;

import model.ImageInterface;

/**
 * Represents the image exporter for .bmp files.
 */
public class BMPExporter extends AbstractImageExporter {

  /**
   * Constructs an exporter with given image and specified file path.
   *
   * @param image    the image object.
   * @param filePath the image file path.
   */
  public BMPExporter(ImageInterface image, String filePath) {
    super(image, filePath);
  }
}
