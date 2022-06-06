package controller;

import model.Image;
import model.Pixel;

/**
 * This class represents a command that can flip images horizontally.
 */
public class FlipHorizontalCommand implements ImageProcessingCommand {

  /**
   * Creates a new command that flips images horizontally.
   */
  public FlipHorizontalCommand() {
  }

  /**
   * Produces the image flipped horizontally.
   *
   * @param img        the image to flip horizontally
   * @param parameters should not have any parameters
   * @return the horizontally flipped image
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
        pixelArray[row][img.getWidth() - col - 1] = new Pixel(img.getMaxValue(),
                currentPixel.getRed(), currentPixel.getGreen(), currentPixel.getBlue());
      }
    }
    return new Image(pixelArray, img.getMaxValue(), img.getWidth(), img.getHeight());
  }
}
