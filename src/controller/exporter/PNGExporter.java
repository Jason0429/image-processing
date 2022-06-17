package controller.exporter;

import java.awt.image.BufferedImage;

import model.ImageInterface;

/**
 * Represents the image exporter for .png files.
 */
public class PNGExporter extends AbstractImageExporter {
  /**
   * Constructs an image exporter with given image and specified file path.
   *
   * @param image    the image object.
   * @param filePath the image file path.
   */
  public PNGExporter(ImageInterface image, String filePath) {
    super(image, filePath, BufferedImage.TYPE_INT_ARGB);
  }
}
