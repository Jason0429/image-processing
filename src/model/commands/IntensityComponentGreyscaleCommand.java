package model.commands;

import model.Pixel;

/**
 * Represents a command that uses the intensity of each pixel to greyscale an image.
 */
public class IntensityComponentGreyscaleCommand
        extends PixelInPlaceProcessingCommand implements ImageProcessingCommand {

  /**
   * Produces the greyscale version of the pixel using the intensity of each pixel.
   *
   * @param pixel the pixel to be processed
   * @return the greyscale version of the pixel using the intensity of the pixel
   * @throws IllegalArgumentException if the pixel is null
   */
  @Override
  protected Pixel processPixel(Pixel pixel) throws IllegalArgumentException {
    return new Pixel(pixel.getMaxValue(), (int) this.getIntensity(pixel),
            (int) this.getIntensity(pixel), (int) this.getIntensity(pixel), pixel.getAlpha());
  }

  /**
   * Returns the average value between red, green, and blue of the pixel.
   *
   * @param p the pixel
   * @return the average of the RGB values
   */
  private double getIntensity(Pixel p) {
    return (p.getRed() + p.getGreen() + p.getBlue()) / 3.0;
  }
}
