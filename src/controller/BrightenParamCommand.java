package controller;

import model.Image;
import model.Pixel;

/**
 * This class represents a command that can brighten images.
 */
public class BrightenParamCommand extends AbstractImageProcessingCommand implements ImageProcessingCommand {

  private final int increment;

  /**
   * Creates a new command that brightens images by a certain increment.
   */
  public BrightenParamCommand(int increment) {
    this.increment = increment;
  }

  /**
   * Produces the image brightened by the specified amount.
   *
   * @param img the image to brighten
   * @return a brightened image
   * @throws IllegalArgumentException if the image is null, or parameters are invalid
   */
  @Override
  public Image process(Image img) throws IllegalArgumentException {
    // TODO: should the increment be positive only? or can it be negative?
    return super.process(img);
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
    // TODO: should the increment be positive only? or can it be negative?
    return new Pixel(pixel.getMaxValue(),
            Math.max(pixel.getRed() + this.increment, pixel.getMaxValue()),
            Math.max(pixel.getGreen() + this.increment, pixel.getMaxValue()),
            Math.max(pixel.getBlue() + this.increment, pixel.getMaxValue()));
  }
}
