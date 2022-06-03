package controller;

import model.Image;

/**
 * This class represents a command that can flip images vertically.
 */
public class FlipVerticalCommand implements ImageProcessingCommand {

  /**
   * Creates a new command that flips images vertically.
   */
  public FlipVerticalCommand() {

  }

  /**
   * Produces the image flipped vertically.
   *
   * @param img the image to flip vertically
   * @return the vertically flipped image
   */
  @Override
  public Image process(Image img) {
    return null;
  }
}
