package model;

/**
 * This interface contains the methods for the image processing model which stores, retrieves, and
 * handles the images.
 */
public interface ImageProcessingModel {

  /**
   * Stores a new image under the specified name.
   *
   * @param name the name of the image
   * @param img the image
   * @throws IllegalArgumentException if the image is null
   */
  public void storeImage(String name, Image img) throws IllegalArgumentException;

  /**
   * Returns the image associated with the specified name.
   * @param name the name of the image
   * @return the image of that name
   * @throws IllegalArgumentException if the image cannot be found
   */
  public Image getImage(String name) throws IllegalArgumentException;

}
