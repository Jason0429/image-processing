package model.commands;

import model.Pixel;

/**
 * Represents a command that uses each pixel's luma to produce a greyscale version.
 */
public class LumaComponentGreyscaleCommand
        extends PixelInPlaceProcessingCommand implements ImageProcessingCommand {

  /**
   * Produces the greyscale version of the pixel using the luma of each pixel.
   *
   * @param pixel the pixel to be processed
   * @return the greyscale version of the pixel using the luma of the pixel
   * @throws IllegalArgumentException if the pixel is null
   */
  @Override
  protected Pixel processPixel(Pixel pixel) throws IllegalArgumentException {
    return new Pixel(pixel.getMaxValue(), (int) pixel.getLuma(),
            (int) pixel.getLuma(), (int) pixel.getLuma());
  }
}
