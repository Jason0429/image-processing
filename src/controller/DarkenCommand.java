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
  public DarkenCommand() {
    this.cmd = new BrightenParamCommand();
  }

  /**
   * Produces the image darkened by the specified amount.
   *
   * @param img        the image to darken
   * @param parameters the amount to darken the image by
   * @return the darkened image
   * @throws IllegalArgumentException if the image is null, or parameters are invalid
   */
  @Override
  public Image process(Image img, int... parameters) throws IllegalArgumentException {
    if (parameters.length != 1) {
      throw new IllegalArgumentException("This command must accept one parameter");
    }
    int increment = parameters[0];
    // TODO: should the increment be positive only?
    return this.cmd.process(img, increment);
  }
}
