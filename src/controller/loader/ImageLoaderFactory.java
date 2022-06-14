package controller.loader;

import model.ExceptionMessage;

/**
 * Represents a factory class for image loader.
 */
public final class ImageLoaderFactory {

  /**
   * Returns the corresponding {@code ImageLoader} based on the image file extension.
   *
   * @param filePath the image file path.
   * @return the corresponding {@code ImageLoader} depending on the file extension.
   * @throws IllegalArgumentException if file extension is unsupported.
   */
  public static ImageLoader getImageLoader(String filePath) throws IllegalArgumentException {
    String fileExtension = filePath.substring(filePath.lastIndexOf('.') + 1);
    switch (fileExtension) {
      case "ppm":
        return new PPMLoader(filePath);
      case "jpeg":
      case "bmp":
      case "png":
        return new ImageIOLoader(filePath);
      default:
        throw new IllegalArgumentException(ExceptionMessage.UNSUPPORTED_FILE_TYPE.toString());
    }
  }
}
