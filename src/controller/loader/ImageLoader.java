package controller.loader;

import model.ExceptionMessage;
import model.ImageInterface;

/**
 * Represents the class that reads image files and produces new {@code ImageInterface} objects.
 */
public final class ImageLoader {

  /**
   * Produces a new {@code ImageInterface} object from a file.
   *
   * @param filePath the image file path.
   * @return a new {@code ImageInterface} object.
   * @throws IllegalArgumentException if the file cannot be found or is corrupted.
   */
  public static ImageInterface load(String filePath) throws IllegalArgumentException {
    String fileExtension = filePath.substring(filePath.lastIndexOf('.') + 1);
    ImageLoaderInterface loader;
    switch (fileExtension) {
      case "ppm":
        loader = new PPMLoader(filePath);
        break;
      case "bmp":
      case "png":
      case "jpeg":
      case "jpg":
        loader = new ImageIOLoader(filePath);
        break;
      default:
        throw new IllegalArgumentException(ExceptionMessage.UNSUPPORTED_FILE_TYPE.toString());
    }
    return loader.load();
  }
}
