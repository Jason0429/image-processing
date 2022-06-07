package controller;

import model.Image;

/**
 * This class represents a command that can darken images.
 */
public class DarkenCommand implements ImageProcessingCommand {
  private final ImageProcessingCommand cmd;

  /**
   * Creates a new command that darkens images by a certain increment.
   */
  public DarkenCommand(int increment) {
    this.cmd = new BrightenParamCommand(increment);
  }

  /**
   * Produces the image darkened by the specified amount.
   *
   * @param img the image to darken
   * @return the darkened image
   * @throws IllegalArgumentException if the image is null, or parameters are invalid
   */
  @Override
  public Image process(Image img) throws IllegalArgumentException {
    // TODO: should the increment be positive only?
    return this.cmd.process(img);
  }
}
