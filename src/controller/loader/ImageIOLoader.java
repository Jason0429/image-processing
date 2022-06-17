package controller.loader;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Image;
import model.ImageInterface;
import model.Pixel;

/**
 * Represents an image loader class using {@code ImageIO} to
 * handle common image file loading functionalities.
 */
public class ImageIOLoader extends AbstractImageLoader {

  /**
   * Constructs an image loader with specified file path.
   *
   * @param filePath the image file path.
   */
  public ImageIOLoader(String filePath) {
    super(filePath);
  }

  @Override
  public ImageInterface loadHelper() throws IOException {
    BufferedImage bufferedImage = ImageIO.read(new FileInputStream(this.filePath));
    int maxValue = 255;
    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();
    Pixel[][] pixelMatrix = new Pixel[height][width];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Color rgb = new Color(bufferedImage.getRGB(col, row));
        Pixel pixel = new Pixel(maxValue, rgb.getRed(), rgb.getGreen(), rgb.getBlue());
        pixelMatrix[row][col] = pixel;
      }
    }

    return new Image(pixelMatrix, maxValue, width, height);
  }
}