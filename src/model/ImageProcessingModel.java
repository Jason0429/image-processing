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
   * @param img  the image
   * @throws IllegalArgumentException if the image is null
   */
  void storeImage(String name, Image img) throws IllegalArgumentException;

  /**
   * Returns the image associated with the specified name.
   *
   * @param name the name of the image
   * @return the image of that name
   * @throws IllegalArgumentException if the image cannot be found
   */
  Image getImage(String name) throws IllegalArgumentException;

  /**
   * Returns an array of the image names stored in the model.
   *
   * @return the image names as a {@code String} array.
   */
  String[] getImageNames();
}
