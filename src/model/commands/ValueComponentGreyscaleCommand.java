package model.commands;

import model.Pixel;

/**
 * This class represents a command that can create greyscale versions of images using the value
 * of each pixel.
 */
public class ValueComponentGreyscaleCommand
        extends PixelInPlaceProcessingCommand implements ImageProcessingCommand {

  /**
   * Produces the greyscale version of the pixel using the value of each pixel.
   *
   * @param pixel the pixel to be processed
   * @return the greyscale version of the pixel using the value of the pixel
   * @throws IllegalArgumentException if the pixel is null
   */
  @Override
  protected Pixel processPixel(Pixel pixel) throws IllegalArgumentException {
    int largestValue = this.pixelLargestRGBValue(pixel);
    return new Pixel(pixel.getMaxValue(), largestValue, largestValue, largestValue);
  }

  /**
   * Returns the largest value between red, green, and blue of the pixel.
   *
   * @param p the pixel
   * @return the largest RGB value
   */
  private int pixelLargestRGBValue(Pixel p) {
    return Math.max(p.getRed(), Math.max(p.getGreen(), p.getBlue()));
  }
}

