package controller;

import model.Image;
import model.Pixel;

/**
 * This class represents a command that can create greyscale versions of images using their green
 * component.
 */
public class GreenComponentGreyscaleParamCommand extends ImageProcessingNoParamCommand implements ImageProcessingCommand {

  /**
   * Creates a new command that creates greyscale images using the green component.
   */
  public GreenComponentGreyscaleParamCommand() {

  }

  /**
   * Produces the greyscale version of the pixel using the green component.
   *
   * @param pixel      the pixel to be processed
   * @param parameters should not have any parameters
   * @return the greyscale version of the pixel using the green component
   * @throws IllegalArgumentException if the pixel is null, or if parameters are passed
   */
  @Override
  Pixel processPixel(Pixel pixel, int... parameters) throws IllegalArgumentException {
    if (parameters.length > 0) {
      throw new IllegalArgumentException("This command does not accept parameters");
    }

    return new Pixel(pixel.getMaxValue(), pixel.getGreen(), pixel.getGreen(), pixel.getGreen());
  }
}
