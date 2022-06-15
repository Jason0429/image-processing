package controller.exporter;

import java.io.IOException;

import model.ExceptionMessage;
import model.ImageInterface;

/**
 * Represents the abstract class for image exporters
 */
public abstract class AbstractImageExporter {
  protected final ImageInterface image;
  protected final String filePath;

  /**
   * Constructs an image exporter with the image to be exported and specified file path.
   *
   * @param image    the image to be exported.
   * @param filePath the image file path.
   */
  public AbstractImageExporter(ImageInterface image, String filePath) {
    this.image = image;
    this.filePath = filePath;
  }

  /**
   * Handles exporting the image to the specified file path.
   *
   * @throws IllegalArgumentException if it is unable to save file.
   */
  public void export() throws IllegalArgumentException {
    try {
      this.exportHelper();
    } catch (IOException e) {
      throw new IllegalArgumentException(ExceptionMessage.UNABLE_TO_SAVE_FILE.toString());
    }
  }

  /**
   * Handles the exporting process.
   */
  protected abstract void exportHelper() throws IOException;
}
