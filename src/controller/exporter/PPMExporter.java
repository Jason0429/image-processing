package controller.exporter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import model.ExceptionMessage;
import model.ImageInterface;

/**
 * Represents an image exporter for .ppm files.
 */
public class PPMExporter implements ImageExporter {
  private final ImageInterface image;
  private final String filePath;

  /**
   * Constructs an image exporter using {@code ImageIO} that
   * takes in an {@code ImageInterface} and file path.
   *
   * @param image    the image to export.
   * @param filePath the file path.
   */
  public PPMExporter(ImageInterface image, String filePath) {
    this.image = image;
    this.filePath = filePath;
  }

  @Override
  public void export() throws IllegalArgumentException {
    try {
      FileOutputStream fos = new FileOutputStream(this.filePath);
      String ppm = image.toPPMString();
      fos.write(ppm.getBytes());
      fos.close();
    } catch (IOException e) {
      throw new IllegalArgumentException(ExceptionMessage.UNABLE_TO_SAVE_FILE.toString());
    }

  }
}
