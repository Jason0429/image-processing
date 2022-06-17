package controller.exporter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.ExceptionMessage;
import model.ImageInterface;
import model.Pixel;

/**
 * Represents the abstract class for image exporters
 */
public abstract class AbstractImageExporter implements ImageExporterInterface {
  protected final ImageInterface image;
  protected final String filePath;
  protected final int imageType;

  /**
   * Constructs an image exporter with the image to be exported and specified file path.
   *
   * @param image    the image to be exported.
   * @param filePath the image file path.
   */
  public AbstractImageExporter(ImageInterface image, String filePath) {
    this(image, filePath, BufferedImage.TYPE_INT_RGB);
  }

  /**
   * Constructs an image exporter with the image to be exported and specified file path
   * and the image type.
   *
   * @param image     the image to be exported.
   * @param filePath  the image file path.
   * @param imageType the image type.
   */
  public AbstractImageExporter(ImageInterface image, String filePath, int imageType) {
    this.image = image;
    this.filePath = filePath;
    this.imageType = imageType;
  }

  @Override
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
