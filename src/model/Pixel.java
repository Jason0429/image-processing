package model;

import java.util.Objects;

/**
 * This class represents a pixel in an image in RGB values.
 */
public class Pixel {
  private final int red;
  private final int green;
  private final int blue;
  private final int alpha;
  private final int maxValue;

  /**
   * Constructs a new pixel with specified RGB values.
   *
   * @param red      the red value, between 0-maxValue
   * @param green    the green value, between 0-maxValue
   * @param blue     the blue value, between 0-maxValue
   * @param maxValue the maximum value of the pixel
   * @throws IllegalArgumentException if any values are not between 0-maxValue
   */
  public Pixel(int maxValue, int red, int green, int blue) throws IllegalArgumentException {
    this(maxValue, red, green, blue, maxValue);
  }

  /**
   * Constructs a new pixel with specified aRGB values.
   *
   * @param red      the red value, between 0-maxValue
   * @param green    the green value, between 0-maxValue
   * @param blue     the blue value, between 0-maxValue
   * @param alpha    the alpha value, between 0-maxValue
   * @param maxValue the maximum value of the pixel
   * @throws IllegalArgumentException if any values are not between 0-maxValue
   */
  public Pixel(int maxValue, int red, int green, int blue, int alpha)
          throws IllegalArgumentException {
    this.maxValue = maxValue;
    if (this.notWithinBounds(red) || this.notWithinBounds(green)
            || this.notWithinBounds(blue) || this.notWithinBounds(alpha)) {
      throw new IllegalArgumentException(ExceptionMessage.PIXEL_RGB_INVALID_RANGE.toString());
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
  }

  /**
   * Returns the red value of the pixel.
   *
   * @return the red value
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Returns the green value of the pixel.
   *
   * @return the green value
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Returns the blue value of the pixel.
   *
   * @return the blue value
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * Returns the alpha value of the pixel.
   *
   * @return the alpha value
   */
  public int getAlpha() {
    return this.alpha;
  }

  /**
   * Returns the max value of the pixel.
   *
   * @return the max value of the pixel
   */
  public int getMaxValue() {
    return this.maxValue;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Pixel) {
      Pixel thatPixel = (Pixel) obj;
      return this.red == thatPixel.red && this.green == thatPixel.green
              && this.blue == thatPixel.blue && this.alpha == thatPixel.alpha
              && this.maxValue == thatPixel.maxValue;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.red, this.green, this.blue, this.alpha, this.maxValue);
  }

  /**
   * Determines if a value is within 0 and maxValue.
   *
   * @param value the value to be tested
   * @return true if the value is within bounds, otherwise false
   */
  private boolean notWithinBounds(int value) {
    return value < 0 || value > this.maxValue;
  }
}
