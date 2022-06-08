package model.commands;

import model.ExceptionMessage;
import model.Image;
import model.Pixel;

/**
 * This class represents a command that can flip images horizontally.
 */
public class FlipHorizontalCommand implements ImageProcessingCommand {

  /**
   * Produces the image flipped horizontally.
   *
   * @param img the image to flip horizontally
   * @return the horizontally flipped image
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public Image process(Image img) throws IllegalArgumentException {
    if (img == null) {
      throw new IllegalArgumentException(
              String.format(ExceptionMessage.SPECIFIC_NULL_ARGUMENT.toString(), "Image"));
    }
    Pixel[][] pixelArray = new Pixel[img.getHeight()][img.getWidth()];
    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        Pixel currentPixel = img.getPixelAt(row, col);
        Pixel processedPixel = new Pixel(img.getMaxValue(),
                currentPixel.getRed(), currentPixel.getGreen(), currentPixel.getBlue());
        pixelArray[row][img.getWidth() - col - 1] = processedPixel;
      }
    }
    return new Image(pixelArray, img.getMaxValue(), img.getWidth(), img.getHeight());
  }
}
