package controller;

import model.Pixel;

/**
 * This class represents a command that can create greyscale versions of images using their green
 * component.
 */
public class GreenComponentGreyscaleCommand extends AbstractImageProcessingCommand implements ImageProcessingCommand {

  /**
   * Creates a new command that creates greyscale images using the green component.
   */
  public GreenComponentGreyscaleCommand() {

  }

  /**
   * Produces the greyscale version of the pixel using the green component.
   *
   * @param pixel      the pixel to be processed
   * @return the greyscale version of the pixel using the green component
   * @throws IllegalArgumentException if the pixel is null, or if parameters are passed
   */
  @Override
  Pixel processPixel(Pixel pixel) throws IllegalArgumentException {
    return new Pixel(pixel.getMaxValue(), pixel.getGreen(), pixel.getGreen(), pixel.getGreen());
  }
}
