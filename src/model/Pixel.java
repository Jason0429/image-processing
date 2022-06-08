package model;

import java.util.Objects;

/**
 * This class represents a pixel in an image in RGB values.
 */
public class Pixel {
  private int red;
  private int green;
  private int blue;
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
    this.maxValue = maxValue;
    if (!this.withinBounds(red) || !this.withinBounds(green) || !this.withinBounds(blue)) {
      throw new IllegalArgumentException(ExceptionMessage.PIXEL_RGB_INVALID_RANGE.toString());
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
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
   * Returns the max value of the pixel.
   *
   * @returns the max value of the pixel
   */
  public int getMaxValue() {
    return this.maxValue;
  }

  /**
   * Sets a new red value, within 0-maxValue. If value exceed maxValue, it will be set to
   * maxValue; if values are less than 0, it will be set to 0.
   *
   * @param r the new red value.
   * @return the updated {@code Pixel}.
   */
  public Pixel setRed(int r) {
    this.red = Math.max(0, Math.min(r, this.maxValue));
    return this;
  }

  /**
   * Sets a new green value, within 0-maxValue. If value exceed maxValue, it will be set to
   * maxValue; if values are less than 0, it will be set to 0.
   *
   * @param g the new green value.
   * @return the updated {@code Pixel}.
   */
  public Pixel setGreen(int g) {
    this.green = Math.max(0, Math.min(g, this.maxValue));
    return this;
  }

  /**
   * Sets a new blue value, within 0-maxValue. If value exceed maxValue, it will be set to
   * maxValue; if values are less than 0, it will be set to 0.
   *
   * @param b the new blue value.
   * @return the updated {@code Pixel}.
   */
  public Pixel setBlue(int b) {
    this.blue = Math.max(0, Math.min(b, this.maxValue));
    return this;
  }

  /**
   * Returns the largest value between red, green, and blue of the pixel.
   *
   * @return the largest RGB value
   */
  public int getLargestRGBValue() {
    return Math.max(this.red, Math.max(this.green, this.blue));
  }

  /**
   * Returns the average value between red, green, and blue of the pixel.
   *
   * @return the average of the RGB values
   */
  public double getIntensity() {
    return (this.red + this.green + this.blue) / 3.0;
  }

  /**
   * Returns the luma value of the pixel.
   * The luma is calculated using 0.2126r + 0.7152g + 0.0722b.
   *
   * @return the luma value of the pixel
   */
  public double getLuma() {
    return (0.2126 * this.red) + (0.7152 * this.green) + (0.0722 * this.blue);
  }

  /**
   * Creates a copy this {@code Pixel} to avoid returning direct references.
   *
   * @return a new {@code Pixel} with the same configurations.
   */
  public Pixel copy() {
    return new Pixel(this.maxValue, this.red, this.green, this.blue);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Pixel) {
      Pixel thatPixel = (Pixel) obj;
      return this.red == thatPixel.red && this.green == thatPixel.green
              && this.blue == thatPixel.blue && this.maxValue == thatPixel.maxValue;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.red, this.green, this.blue, this.maxValue);
  }

  /**
   * Determines if a value is within 0 and maxValue.
   *
   * @param value the value to be tested
   * @return true if the value is within bounds, otherwise false
   */
  private boolean withinBounds(int value) {
    return value >= 0 && value <= this.maxValue;
  }


}
