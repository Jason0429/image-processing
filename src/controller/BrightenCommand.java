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
    Image processedImage = img.copy();
    for (int row = 0; row < processedImage.getHeight(); row++) {
      for (int col = 0; col < processedImage.getWidth(); col++) {
        Pixel currentPixel = processedImage.getPixelAt(row, col);
        int currentRed = currentPixel.getRed();
        int currentGreen = currentPixel.getGreen();
        int currentBlue = currentPixel.getBlue();
        currentPixel.setRed(currentRed + this.increment)
                .setGreen(currentGreen + this.increment)
                .setBlue(currentBlue + this.increment);
      }
    }
    return processedImage;
  }
}
