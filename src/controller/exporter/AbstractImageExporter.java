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
  protected void exportHelper() throws IOException {
    BufferedImage img = new BufferedImage(this.image.getWidth(),
            this.image.getHeight(), this.imageType);
    String fileExtension = this.filePath.substring(this.filePath.lastIndexOf('.') + 1);
    for (int row = 0; row < this.image.getHeight(); row++) {
      for (int col = 0; col < this.image.getWidth(); col++) {
        Pixel currentPixel = this.image.getPixelAt(row, col);
        int rgb = new Color(currentPixel.getRed(), currentPixel.getGreen(),
                currentPixel.getBlue(), currentPixel.getAlpha()).getRGB();
        img.setRGB(col, row, rgb);
      }
    }
    ImageIO.write(img, fileExtension, new File(this.filePath));
  }
}
