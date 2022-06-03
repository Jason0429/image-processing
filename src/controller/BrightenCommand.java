package controller;

import model.Image;
import model.Pixel;

/**
 * This class represents a command that can brighten images.
 */
public class BrightenCommand implements ImageProcessingCommand {
  private final int increment;

  /**
   * Creates a new command that brightens images by a certain increment.
   *
   * @param increment the amount to brighten images by
   */
  public BrightenCommand(int increment) {
    // TODO: should be non-negative?
    this.increment = increment;
  }

  /**
   * Produces the image brightened by the specified amount.
   *
   * @param img the image to brighten
   * @return a brightened image
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public Image process(Image img) throws IllegalArgumentException {
    Pixel[][] pixelArray = new Pixel[img.getHeight()][img.getWidth()];
    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        Pixel currentPixel = img.getPixelAt(row, col);
        pixelArray[row][col] = new Pixel(img.getMaxValue(),
                Math.max(currentPixel.getRed() + this.increment, img.getMaxValue()),
                Math.max(currentPixel.getGreen() + this.increment, img.getMaxValue()),
                Math.max(currentPixel.getBlue() + this.increment, img.getMaxValue()));
      }
    }

    return new Image(pixelArray, img.getMaxValue(), img.getWidth(), img.getHeight());
  }
}
