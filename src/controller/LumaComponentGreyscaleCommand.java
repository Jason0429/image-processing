package controller;

import model.Pixel;

public class LumaComponentGreyscaleCommand
        extends ImageProcessingNoParamCommand implements ImageProcessingCommand {

  /**
   * Creates a new command that creates greyscale images using the luma of each pixel.
   */
  public LumaComponentGreyscaleCommand() {

  }

  /**
   * Produces the greyscale version of the pixel using the luma of each pixel.
   *
   * @param pixel      the pixel to be processed
   * @param parameters should not have any parameters
   * @return the greyscale version of the pixel using the luma of the pixel
   * @throws IllegalArgumentException if the pixel is null, or if parameters are passed
   */
  @Override
  Pixel processPixel(Pixel pixel, int... parameters) throws IllegalArgumentException {
    if (parameters.length > 0) {
      throw new IllegalArgumentException("This command does not accept parameters");
    }

    return new Pixel(pixel.getMaxValue(), (int) pixel.getLuma(),
            (int) pixel.getLuma(), (int) pixel.getLuma());
  }
}
