package controller;

import model.Pixel;

/**
 * This class represents a command that can create greyscale versions of images using the value
 * of each pixel.
 */
public class ValueComponentGreyscaleCommand
        extends ImageProcessingNoParamCommand implements ImageProcessingCommand {

  /**
   * Creates a new command that creates greyscale images using the value of each pixel.
   */
  public ValueComponentGreyscaleCommand() {

  }

  /**
   * Produces the greyscale version of the pixel using the value of each pixel.
   *
   * @param pixel      the pixel to be processed
   * @param parameters should not have any parameters
   * @return the greyscale version of the pixel using the value of the pixel
   * @throws IllegalArgumentException if the pixel is null, or if parameters are passed
   */
  @Override
  Pixel processPixel(Pixel pixel, int... parameters) throws IllegalArgumentException {
    if (parameters.length > 0) {
      throw new IllegalArgumentException("This command does not accept parameters");
    }

    return new Pixel(pixel.getMaxValue(), pixel.getValue(), pixel.getValue(), pixel.getValue());
  }
}

