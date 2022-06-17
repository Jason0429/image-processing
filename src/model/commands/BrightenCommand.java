package model.commands;

import model.Pixel;

/**
 * Represents a command to brighten (or darken) images.
 */
public class BrightenCommand
        extends PixelInPlaceProcessingCommand implements ImageProcessingCommand {

  private final int increment;

  /**
   * Creates a new command that brightens images by a certain increment.
   */
  public BrightenCommand(int increment) {
    this.increment = increment;
  }

  /**
   * Produces the pixel brightened by the specified amount.
   *
   * @param pixel the pixel to brighten
   * @return a brightened pixel
   * @throws IllegalArgumentException if the image is null, or parameters are invalid
   */
  @Override
  protected Pixel processPixel(Pixel pixel) throws IllegalArgumentException {
    if (this.increment < 0) {
      return new Pixel(pixel.getMaxValue(),
              Math.max(pixel.getRed() + this.increment, 0),
              Math.max(pixel.getGreen() + this.increment, 0),
              Math.max(pixel.getBlue() + this.increment, 0),
              Math.max(pixel.getAlpha() + this.increment, 0));
    }
    return new Pixel(pixel.getMaxValue(),
            Math.min(pixel.getRed() + this.increment, pixel.getMaxValue()),
            Math.min(pixel.getGreen() + this.increment, pixel.getMaxValue()),
            Math.min(pixel.getBlue() + this.increment, pixel.getMaxValue()),
            Math.max(pixel.getAlpha() + this.increment, 0));
  }
}
