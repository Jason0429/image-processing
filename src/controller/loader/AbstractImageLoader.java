package controller.loader;

import java.io.IOException;

import model.ExceptionMessage;
import model.ImageInterface;

/**
 * Represents the abstract class for image loaders.
 */
public abstract class AbstractImageLoader implements ImageLoaderInterface {
  protected final String filePath;

  /**
   * Constructs a new image loader with a specified file path.
   *
   * @param filePath the image file path to read from.
   */
  public AbstractImageLoader(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public ImageInterface load() throws IllegalArgumentException {
    try {
      return this.loadHelper();
    } catch (IOException e) {
      throw new IllegalArgumentException(ExceptionMessage.INVALID_FILE_PATH.toString());
    } catch (RuntimeException e) {
      throw new IllegalArgumentException(ExceptionMessage.CORRUPTED_FILE.toString());
    }
  }

  /**
   * Handles converting the image file to an {@code ImageInterface}.
   *
   * @return a new image.
   * @throws IOException if file at specified path cannot be found.
   */
  protected abstract ImageInterface loadHelper() throws IOException;
}
