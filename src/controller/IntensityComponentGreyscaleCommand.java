package controller;

import model.Pixel;

public class IntensityComponentGreyscaleCommand
        extends AbstractImageProcessingCommand implements ImageProcessingCommand {

  /**
   * Creates a new command that creates greyscale images using the intensity of each pixel.
   */
  public IntensityComponentGreyscaleCommand() {

  }

  /**
   * Produces the greyscale version of the pixel using the intensity of each pixel.
   *
   * @param pixel      the pixel to be processed
   * @return the greyscale version of the pixel using the intensity of the pixel
   * @throws IllegalArgumentException if the pixel is null, or if parameters are passed
   */
  @Override
  Pixel processPixel(Pixel pixel) throws IllegalArgumentException {
    return new Pixel(pixel.getMaxValue(), (int) pixel.getIntensity(),
            (int) pixel.getIntensity(), (int) pixel.getIntensity());
  }
}
