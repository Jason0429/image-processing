package model.commands;

import model.Pixel;

/**
 * Represents the command to apply a blue component greyscale to an image.
 */
public class BlueComponentGreyscaleCommand
        extends AbstractImageProcessingCommand implements ImageProcessingCommand {
  
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

