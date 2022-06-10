package model.commands;

import model.Pixel;

/**
 * Represents a command that produces a sepia version.
 */
public class SepiaProcessingCommand extends AbstractImageProcessingCommand implements ImageProcessingCommand {

  /**
   * Produces the sepia version of the pixel.
   *
   * @param pixel the pixel to be processed
   * @return the sepia version of the pixel
   * @throws IllegalArgumentException if the pixel is null
   */
  @Override
  protected Pixel processPixel(Pixel pixel) throws IllegalArgumentException {
    int red = (int) (0.393 * pixel.getRed() + 0.769 * pixel.getGreen() + 0.189 * pixel.getBlue());
    int green = (int) (0.349 * pixel.getRed() + 0.686 * pixel.getGreen() + 0.168 * pixel.getBlue());
    int blue = (int) (0.272 * pixel.getRed() + 0.534 * pixel.getGreen() + 0.131 * pixel.getBlue());
    return new Pixel(pixel.getMaxValue(),
            Math.max(0, Math.min(pixel.getMaxValue(), red)),
            Math.max(0, Math.min(pixel.getMaxValue(), green)),
            Math.max(0, Math.min(pixel.getMaxValue(), blue)));
  }
}