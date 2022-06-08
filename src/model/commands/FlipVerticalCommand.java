package model.commands;

import model.Image;
import model.Pixel;

/**
 * This class represents a command that can flip images vertically.
 */
public class FlipVerticalCommand implements ImageProcessingCommand {

  /**
   * Creates a new command that flips images vertically.
   */
  public FlipVerticalCommand() {

  }

  /**
   * Produces the image flipped vertically.
   *
   * @param img the image to flip vertically
   * @return the vertically flipped image
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public Image process(Image img) throws IllegalArgumentException {
    Pixel[][] pixelArray = new Pixel[img.getHeight()][img.getWidth()];
    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        Pixel currentPixel = img.getPixelAt(row, col);
        pixelArray[img.getHeight() - row - 1][col] = new Pixel(img.getMaxValue(),
                currentPixel.getRed(), currentPixel.getGreen(), currentPixel.getBlue());
      }
    }
    return new Image(pixelArray, img.getMaxValue(), img.getWidth(), img.getHeight());
  }
}
