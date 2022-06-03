package controller;

import model.Image;

/**
 * This class represents a command that can darken images.
 */
public class DarkenCommand implements ImageProcessingCommand {
  private ImageProcessingCommand cmd;

  /**
   * Creates a new command that darkens images by a certain increment.
   *
   * @param increment
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
   */
  @Override
  public Image process(Image img) {
    return this.cmd.process(img);
  }
}
