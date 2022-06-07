package controller;

import model.Pixel;

/**
 * This class represents a command that can create greyscale versions of images using their red
 * component.
 */
public class RedComponentGreyscaleCommand
        extends AbstractImageProcessingCommand implements ImageProcessingCommand {

  /**
   * Creates a new command that creates greyscale images using the red component.
   */
  public RedComponentGreyscaleCommand() {

  }

  /**
   * Produces the greyscale version of the pixel using the red component.
   *
   * @param pixel      the pixel to be processed
   * @return the greyscale version of the pixel using the red component
   * @throws IllegalArgumentException if the pixel is null, or if parameters are passed
   */
  @Override
  Pixel processPixel(Pixel pixel) throws IllegalArgumentException {
    return new Pixel(pixel.getMaxValue(), pixel.getRed(), pixel.getRed(), pixel.getRed());
  }
}
