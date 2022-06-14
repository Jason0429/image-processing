package controller.loader;

import model.ImageInterface;

/**
 * This interface reads image files and produces new {@code ImageInterface} objects.
 */
public interface ImageLoader {

  /**
   * Produces a new {@code ImageInterface} object from a file.
   *
   * @return a new {@code ImageInterface} object.
   * @throws IllegalArgumentException if the file cannot be found or is corrupted.
   */
  ImageInterface load() throws IllegalArgumentException;
}
