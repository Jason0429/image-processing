package controller;

import model.Pixel;

public class BlueComponentGreyscaleCommand
        extends AbstractImageProcessingCommand implements ImageProcessingCommand {

  /**
   * Creates a new command that creates greyscale images using the blue component.
   */
  public BlueComponentGreyscaleCommand() {

  }

  /**
   * Produces the greyscale version of the pixel using the blue component.
   *
   * @param pixel the pixel to be processed
   * @return the greyscale version of the pixel using the blue component
   * @throws IllegalArgumentException if the pixel is null
   */
  @Override
  Pixel processPixel(Pixel pixel) throws IllegalArgumentException {
    return new Pixel(pixel.getMaxValue(), pixel.getBlue(), pixel.getBlue(), pixel.getBlue());
  }
}

