package controller;

import model.Image;

/**
 * This class represents a command that can brighten images.
 */
public class BrightenCommand implements ImageProcessingCommand {
  private final int increment;

  /**
   * Creates a new command that brightens images by a certain increment.
   *
   * @param increment the amount to brighten images by
   */
  public BrightenCommand(int increment) {
    // TODO: should be non-negative?
    this.increment = increment;
  }

  /**
   * Produces the image brightened by the specified amount.
   *
   * @param img the image to brighten
   * @return a brightened image
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public Image process(Image img) throws IllegalArgumentException {
    return null;
  }
}
