package controller;

import model.Image;

/**
 * This class represents a command that can flip images horizontally.
 */
public class FlipHorizontalCommand implements ImageProcessingCommand {

  /**
   * Creates a new command that flips images horizontally.
   */
  public FlipHorizontalCommand() {

  }

  /**
   * Produces the image flipped horizontally.
   *
   * @param img the image to flip horizontally
   * @return the horizontally flipped image
   */
  @Override
  public Image process(Image img) {
    return null;
  }
}