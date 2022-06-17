package model.commands;

import model.Pixel;

/**
 * This class represents a command that can create greyscale versions of images using their red
 * component.
 */
public class RedComponentGreyscaleCommand
        extends PixelInPlaceProcessingCommand implements ImageProcessingCommand {

  /**
   * Produces the greyscale version of the pixel using the red component.
   *
   * @param pixel the pixel to be processed
   * @return the greyscale version of the pixel using the red component
   * @throws IllegalArgumentException if the pixel is null
   */
  @Override
  protected Pixel processPixel(Pixel pixel) throws IllegalArgumentException {
    return new Pixel(pixel.getMaxValue(), pixel.getRed(),
            pixel.getRed(), pixel.getRed(), pixel.getAlpha());
  }
}
