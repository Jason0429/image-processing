package controller.exporter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.ImageInterface;
import model.Pixel;

/**
 * Represents an image exporter class using {@code ImageIO} to
 * handle common image file loading functionalities.
 */
public class ImageIOExporter extends AbstractImageExporter {
  public ImageIOExporter(ImageInterface image, String filePath) {
    super(image, filePath);
  }

  public ImageIOExporter(ImageInterface image, String filePath, int imageType) {
    super(image, filePath, imageType);
  }

  @Override
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
