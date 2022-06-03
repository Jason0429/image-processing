package controller;

import model.Image;

/**
 * This class represents a command that can darken images.
 */
public class DarkenCommand implements ImageProcessingCommand {
  private final ImageProcessingCommand cmd;

  /**
   * Creates a new command that darkens images by a certain increment.
   *
   * @param increment amount to darken the images by
   */
  public DarkenCommand(int increment) {
    // TODO: should be non-negative?
    this.cmd = new BrightenCommand(-increment);
  }

  /**
   * Produces the image darkened by the specified amount.
   *
   * @param img the image to darken
   * @return the darkened image
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public Image process(Image img) throws IllegalArgumentException {
    return this.cmd.process(img);
  }
}
