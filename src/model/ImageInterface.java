package model;

/**
 * This interface contains the methods for images and retrieving information on them.
 */
public interface ImageInterface {

  /**
   * Returns the width of the image.
   *
   * @return the width of the image
   */
  int getWidth();

  /**
   * Returns the height of the image.
   *
   * @return the height of the image
   */
  int getHeight();

  /**
   * Returns the max pixel value of the image.
   *
   * @return the max pixel value of the image
   */
  int getMaxValue();

  /**
   * Returns the pixel at the specified location in the image.
   *
   * @param row the row of the pixel
   * @param col the col of the pixel
   * @return the pixel at the specified location
   * @throws IllegalArgumentException if the row or col are out of bounds
   */
  Pixel getPixelAt(int row, int col) throws IllegalArgumentException;

  /**
   * Returns the PPM representation of the image as a String.
   *
   * @return the PPM representation of the image as a String.
   */
  String toPPMString();
}
