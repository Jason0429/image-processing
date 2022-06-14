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
 * Represents an image exporter using {@code ImageIO}.
 */
public class ImageIOExporter implements ImageExporter {
  private final ImageInterface image;
  private final String filePath;

  /**
   * Constructs an image exporter using {@code ImageIO} that
   * takes in an {@code ImageInterface} and file path.
   *
   * @param image    the image to export.
   * @param filePath the file path.
   */
  public ImageIOExporter(ImageInterface image, String filePath) {
    this.image = image;
    this.filePath = filePath;
  }

  @Override
  public void export() throws IllegalArgumentException {
    String fileExtension = this.filePath.substring(this.filePath.lastIndexOf('.') + 1);
    BufferedImage img = new BufferedImage(this.image.getWidth(), this.image.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    for (int row = 0; row < this.image.getHeight(); row++) {
      for (int col = 0; col < this.image.getWidth(); col++) {
        Pixel currentPixel = this.image.getPixelAt(row, col);
        img.setRGB(col, row, new Color(currentPixel.getRed(), currentPixel.getGreen(),
                currentPixel.getBlue()).getRGB());
      }
    }
    try {
      ImageIO.write(img, fileExtension, new File(this.filePath));
    } catch (IOException e) {
      throw new IllegalArgumentException(ExceptionMessage.UNABLE_TO_SAVE_FILE.toString());
    }
  }
}
