package controller;

import model.Pixel;

public class BlueComponentGreyscaleParamCommand
        extends ImageProcessingNoParamCommand implements ImageProcessingCommand {

  /**
   * Creates a new command that creates greyscale images using the blue component.
   */
  public BlueComponentGreyscaleParamCommand() {

  }

  /**
   * Produces the greyscale version of the pixel using the blue component.
   *
   * @param pixel      the pixel to be processed
   * @param parameters should not have any parameters
   * @return the greyscale version of the pixel using the blue component
   * @throws IllegalArgumentException if the pixel is null, or if parameters are passed
   */
  @Override
  Pixel processPixel(Pixel pixel, int... parameters) throws IllegalArgumentException {
    if (parameters.length > 0) {
      throw new IllegalArgumentException("This command does not accept parameters");
    }

    return new Pixel(pixel.getMaxValue(), pixel.getBlue(), pixel.getBlue(), pixel.getBlue());
  }
}

