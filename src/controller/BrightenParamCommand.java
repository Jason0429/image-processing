package controller;

import model.Image;
import model.Pixel;

/**
 * This class represents a command that can brighten images.
 */
public class BrightenParamCommand extends ImageProcessingParamCommand implements ImageProcessingCommand {

  /**
   * Creates a new command that brightens images by a certain increment.
   */
  public BrightenParamCommand() {
  }

  /**
   * Produces the image brightened by the specified amount.
   *
   * @param img        the image to brighten
   * @param parameters the amount to brighten the image by
   * @return a brightened image
   * @throws IllegalArgumentException if the image is null, or parameters are invalid
   */
  @Override
  public Image process(Image img, int... parameters) throws IllegalArgumentException {
    // TODO: should the increment be positive only? or can it be negative?
    if (parameters.length != 1) {
      throw new IllegalArgumentException("This command must accept one parameter");
    }
    int increment = parameters[0];
    return super.process(img, increment);
  }

  /**
   * Produces the pixel brightened by the specified amount.
   *
   * @param pixel      the pixel to brighten
   * @param parameters the amount to brighten by
   * @return a brightened pixel
   * @throws IllegalArgumentException if the image is null, or parameters are invalid
   */
  @Override
  Pixel processPixel(Pixel pixel, int ...parameters) throws IllegalArgumentException {
    // TODO: should the increment be positive only? or can it be negative?
    if (parameters.length != 1) {
      throw new IllegalArgumentException("This command must accept one parameter");
    }
    return new Pixel(pixel.getMaxValue(),
            Math.max(pixel.getRed() + parameters[0], pixel.getMaxValue()),
            Math.max(pixel.getGreen() + parameters[0], pixel.getMaxValue()),
            Math.max(pixel.getBlue() + parameters[0], pixel.getMaxValue()));
  }
}
