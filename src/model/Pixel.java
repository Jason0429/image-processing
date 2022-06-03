package model;

/**
 * This class represents a pixel in an image in RGB values.
 */
public class Pixel {
  private int red;
  private int green;
  private int blue;

  private int maxValue;

  /**
   * Constructs a new pixel.
   *
   * @param red the red value, between 0-maxValue
   * @param green the green value, between 0-maxValue
   * @param blue the blue value, between 0-maxValue
   * @param maxValue the maximum value of the pixel
   * @throws IllegalArgumentException if any values are not between 0-maxValue
   */
  public Pixel(int maxValue, int red, int green, int blue) throws IllegalArgumentException {
    this.maxValue = maxValue;
    if (!this.withinBounds(red) || !this.withinBounds(green) || !this.withinBounds(blue)) {
      throw new IllegalArgumentException("Values must be between 0 and the maximum value");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Returns the red value of the pixel.
   * @return the red value
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Returns the green value of the pixel.
   * @return the green value
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Returns the blue value of the pixel.
   * @return the blue value
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * Sets a new red value, within 0-maxValue. If value exceed maxValue, it will be set to
   * maxValue; if values are less than 0, it will be set to 0.
   */
  public void setRed(int newRed) {
    this.red = Math.max(0, Math.min(newRed, this.maxValue));
  }

  /**
   * Sets a new green value, within 0-maxValue. If value exceed maxValue, it will be set to
   * maxValue; if values are less than 0, it will be set to 0.
   */
  public void setGreen(int newGreen) {
    this.green = Math.max(0, Math.min(newGreen, this.maxValue));
  }

  /**
   * Sets a new blue value, within 0-maxValue. If value exceed maxValue, it will be set to
   * maxValue; if values are less than 0, it will be set to 0.
   */
  public void setBlue(int newBlue) {
    this.blue = Math.max(0, Math.min(newBlue, this.maxValue));
  }

  /**
   * Returns the largest value between red, green, and blue of the pixel.
   * @return the largest RGB value
   */
  public int getValue() {
    return Math.max(this.red, Math.max(this.green, this.blue));
  }

  /**
   * Returns the average value between red, green, and blue of the pixel.
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
    return 0.2126 * this.red + 0.7152 * this.green + 0.0722 * this.blue;
  }


  /**
   * Determines if a value is within 0 and maxValue.
   * @param value the value to be tested
   * @return true if the value is within bounds, otherwise false
   */
  private boolean withinBounds(int value) {
    return value >= 0 && value <= this.maxValue;
  }
}
