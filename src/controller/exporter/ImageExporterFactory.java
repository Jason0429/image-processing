package controller.exporter;

import controller.loader.ImageIOLoader;
import controller.loader.ImageLoader;
import controller.loader.PPMLoader;
import model.ExceptionMessage;
import model.ImageInterface;

/**
 * Represents a factory class for image exporter.
 */
public final class ImageExporterFactory {
  /**
   * Returns the corresponding {@code ImageExporter} based on the image file extension.
   *
   * @param image    the image to be exported.
   * @param filePath the image file path.
   * @return the corresponding {@code ImageLoader} depending on the file extension.
   * @throws IllegalArgumentException if file extension is unsupported.
   */
  public static ImageExporter getImageExporter(ImageInterface image, String filePath) throws IllegalArgumentException {
    String fileExtension = filePath.substring(filePath.lastIndexOf('.') + 1);
    switch (fileExtension) {
      case "ppm":
        return new PPMExporter(image, filePath);
      case "jpeg":
      case "bmp":
      case "png":
        return new ImageIOExporter(image, filePath);
      default:
        throw new IllegalArgumentException(ExceptionMessage.UNSUPPORTED_FILE_TYPE.toString());
    }
  }
}
