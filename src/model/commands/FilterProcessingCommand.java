package model.commands;

import controller.transformation.ImageFilter;
import model.ImageInterface;

/**
 * This abstract class represents commands that filter images.
 */
public abstract class FilterProcessingCommand implements ImageProcessingCommand {

  @Override
  public ImageInterface process(ImageInterface img) throws IllegalArgumentException {
    return ImageFilter.filter(this.getKernel(), img);
  }

  /**
   * Gets the appropriate kernel for the filter, depending on the implementation.
   *
   * @return a kernel
   */
  protected abstract double[][] getKernel();
}
