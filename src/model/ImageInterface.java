package model;

/**
 * This interface contains the methods for images and retrieving information on them.
 */
public interface ImageInterface {

  /**
   * Returns the width of the image.
   * @return the width of the image
   */
  public int getWidth();

  /**
   * Returns the height of the image.
   * @return the height of the image
   */
  public int getHeight();

  /**
   * Returns the max pixel value of the image.
   * @returns the max pixel value of the image
   */
  public int getMaxValue();

  /**
   * Returns the pixel at the specified location in the image.
   * @param row the row of the pixel
   * @param col the col of the pixel
   * @return the pixel at the specified location
   * @throws IllegalArgumentException if the row or col are out of bounds
   */
  public Pixel getPixelAt(int row, int col) throws IllegalArgumentException;

  /**
   * Returns the PPM representation of the image as a String.
   * @return the PPM representation of the image as a String.
   */
  public String toPPM();

}
