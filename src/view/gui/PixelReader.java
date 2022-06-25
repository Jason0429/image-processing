package view.gui;

import model.Pixel;

/**
 * Contains static methods that read pixel values.
 */
public class PixelReader {

  /**
   * Gets the red component of a pixel.
   *
   * @param pixel the pixel
   * @return the red component
   */
  public static int getRed(Pixel pixel) {
    return pixel.getRed();
  }

  /**
   * Gets the green component of a pixel.
   *
   * @param pixel the pixel
   * @return the green component
   */
  public static int getGreen(Pixel pixel) {
    return pixel.getGreen();
  }

  /**
   * Gets the blue component of a pixel.
   *
   * @param pixel the pixel
   * @return the blue component
   */
  public static int getBlue(Pixel pixel) {
    return pixel.getBlue();
  }

  /**
   * Gets the intensity of a pixel.
   *
   * @param pixel the pixel
   * @return the intensity
   */
  public static int getIntensity(Pixel pixel) {
    return (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
  }
}
