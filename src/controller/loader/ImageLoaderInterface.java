package controller.loader;

import model.ImageInterface;

/**
 * Contains the methods needed to load an image.
 */
public interface ImageLoaderInterface {

  /**
   * Produces a new {@code ImageInterface} object from a file.
   *
   * @return a new {@code ImageInterface} object.
   * @throws IllegalArgumentException if the file cannot be found or is corrupted.
   */
  ImageInterface load() throws IllegalArgumentException;
}
