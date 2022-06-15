package controller.exporter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.ImageInterface;
import model.Pixel;

/**
 * Represents an image exporter using {@code ImageIO}.
 */
public class ImageIOExporter extends AbstractImageExporter {

  /**
   * Constructs an image exporter using {@code ImageIO} that
   * takes in an {@code ImageInterface} and file path.
   *
   * @param image    the image to export.
   * @param filePath the file path.
   */
  public ImageIOExporter(ImageInterface image, String filePath) {
    super(image, filePath);
  }

  @Override
  public void exportHelper() throws IOException {
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
    ImageIO.write(img, fileExtension, new File(this.filePath));
  }
}
