package controller.loader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.ExceptionMessage;
import model.Image;
import model.ImageInterface;
import model.Pixel;

/**
 * Represents an image loader class using {@code ImageIO} to
 * handle common image file loading functionalities.
 */
public class ImageIOLoader implements ImageLoader {
  protected final String filePath;

  /**
   * Constructs an image loader with specified file path.
   *
   * @param filePath the image file path.
   */
  public ImageIOLoader(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public ImageInterface load() throws IllegalArgumentException {
    try {
      BufferedImage bufferedImage = ImageIO.read(new FileInputStream(this.filePath));
      int maxValue = 255;
      int width = bufferedImage.getWidth();
      int height = bufferedImage.getHeight();
      Pixel[][] pixelMatrix = new Pixel[height][width];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Color rgb = new Color(bufferedImage.getRGB(i, j));
          Pixel pixel = new Pixel(255, rgb.getRed(), rgb.getGreen(), rgb.getBlue());
          pixelMatrix[i][j] = pixel;
        }
      }
      return new Image(pixelMatrix, maxValue, width, height);
    } catch (IOException e) {
      throw new IllegalArgumentException(ExceptionMessage.INVALID_FILE_PATH.toString());
    }
  }
}