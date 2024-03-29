package model.commands;

import controller.transformation.ImageFilter;
import model.ImageInterface;

/**
 * This class represents a command that sharpens images.
 */
public class SharpenCommand extends FilterProcessingCommand implements ImageProcessingCommand {

  @Override
  public ImageInterface process(ImageInterface img) throws IllegalArgumentException {
    return ImageFilter.filter(Kernel.sharpen(), img);
  }

  @Override
  protected double[][] getKernel() {
    return Kernel.sharpen();
  }
}
