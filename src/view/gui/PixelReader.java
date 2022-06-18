package view.gui;

import model.Pixel;

public class PixelReader {

  public static int getRed(Pixel pixel) {
    return pixel.getRed();
  }

  public static int getGreen(Pixel pixel) {
    return pixel.getGreen();
  }

  public static int getBlue(Pixel pixel) {
    return pixel.getBlue();
  }

  public static int getIntensity(Pixel pixel) {
    return (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
  }
}
