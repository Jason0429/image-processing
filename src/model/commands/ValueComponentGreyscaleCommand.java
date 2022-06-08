package model.commands;

import model.Pixel;

/**
 * This class represents a command that can create greyscale versions of images using the value
 * of each pixel.
 */
public class ValueComponentGreyscaleCommand
        extends AbstractImageProcessingCommand implements ImageProcessingCommand {

  /**
   * Produces the greyscale version of the pixel using the value of each pixel.
   *
   * @param pixel the pixel to be processed
   * @return the greyscale version of the pixel using the value of the pixel
   * @throws IllegalArgumentException if the pixel is null
   */
  @Override
  Pixel processPixel(Pixel pixel) throws IllegalArgumentException {
    if (pixel == null) {
      throw new IllegalArgumentException("Pixel cannot be null");
    }
    return new Pixel(pixel.getMaxValue(), pixel.getLargestRGBValue(),
            pixel.getLargestRGBValue(), pixel.getLargestRGBValue());
  }
}

