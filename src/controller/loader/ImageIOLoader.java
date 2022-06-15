package controller.loader;

import java.awt.*;
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
    System.out.print(String.format("Width: %s, Height: %s", width, height));
    Pixel[][] pixelMatrix = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color rgb = new Color(bufferedImage.getRGB(j, i));
        Pixel pixel = new Pixel(maxValue, rgb.getRed(), rgb.getGreen(), rgb.getBlue());
        pixelMatrix[i][j] = pixel;
      }
    }
    return new Image(pixelMatrix, maxValue, width, height);
  }
}