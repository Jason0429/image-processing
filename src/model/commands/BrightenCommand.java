package model.commands;

import model.Image;
import model.Pixel;

/**
 * Represents a command to brighten (or darken) images.
 */
public class BrightenCommand
        extends AbstractImageProcessingCommand implements ImageProcessingCommand {

  private final int increment;

  /**
   * Creates a new command that brightens images by a certain increment.
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
        currentPixel.setRed(Math.max(0, Math.min(currentPixel.getMaxValue(),
                        currentRed + this.increment)))
                .setGreen(Math.max(0, Math.min(currentPixel.getMaxValue(),
                        currentGreen + this.increment)))
                .setBlue(Math.max(0, Math.min(currentPixel.getMaxValue(),
                        currentBlue + this.increment)));
      }
    }
    return processedImage;
  }

  /**
   * Produces the pixel brightened by the specified amount.
   *
   * @param pixel the pixel to brighten
   * @return a brightened pixel
   * @throws IllegalArgumentException if the image is null, or parameters are invalid
   */
  @Override
  Pixel processPixel(Pixel pixel) throws IllegalArgumentException {
    return new Pixel(pixel.getMaxValue(),
            Math.max(pixel.getRed() + this.increment, pixel.getMaxValue()),
            Math.max(pixel.getGreen() + this.increment, pixel.getMaxValue()),
            Math.max(pixel.getBlue() + this.increment, pixel.getMaxValue()));
  }
}
