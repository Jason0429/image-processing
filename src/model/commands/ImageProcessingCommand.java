package model.commands;

import model.Image;
import model.ImageInterface;

/**
 * This interface contains the methods for the commands that process images.
 */
public interface ImageProcessingCommand {

  /**
   * Produces a new image that has been processed. Specific details about how the
   * image has been
   * processed vary by implementation.
   *
   * @param img the image to be processed
   * @return the processed image
   * @throws IllegalArgumentException if the image is null
   */
  ImageInterface process(ImageInterface img) throws IllegalArgumentException;
}
