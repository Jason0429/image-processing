package controller.exporter;

import java.awt.image.BufferedImage;

import model.ExceptionMessage;
import model.ImageInterface;

/**
 * Represents the class that exports {@code ImageInterface} objects to image files.
 */
public final class ImageExporter {
  /**
   * Exports the {@code ImageInterface} to a formatted file to a specific file path destination.
   *
   * @param image    image the image to be exported.
   * @param filePath the image file path.
   * @throws IllegalArgumentException if image file cannot be saved successfully.
   */
  public static void export(ImageInterface image, String filePath) throws IllegalArgumentException {
    String fileExtension = filePath.substring(filePath.lastIndexOf('.') + 1);
    switch (fileExtension) {
      case "ppm":
        new PPMExporter(image, filePath).export();
        break;
      case "png":
        new ImageIOExporter(image, filePath, BufferedImage.TYPE_INT_ARGB).export();
        break;
      case "bmp":
      case "jpeg":
      case "jpg":
        new ImageIOExporter(image, filePath).export();
        break;
      default:
        throw new IllegalArgumentException(ExceptionMessage.UNSUPPORTED_FILE_TYPE.toString());
    }
  }
}

