package controller;

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
   * @param img        the image to flip vertically
   * @param parameters should not have any parameters
   * @return the vertically flipped image
   * @throws IllegalArgumentException if the image is null, or if parameters are passed
   */
  @Override
  public Image process(Image img, int... parameters) throws IllegalArgumentException {
    if (parameters.length > 0) {
      throw new IllegalArgumentException("This command does not accept parameters");
    }
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
