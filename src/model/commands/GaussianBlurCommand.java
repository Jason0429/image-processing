package model.commands;

import controller.transformation.ImageFilter;
import model.ImageInterface;

/**
 * This class represents a command that applies a gaussian blur to an image.
 */
public class GaussianBlurCommand extends FilterProcessingCommand implements ImageProcessingCommand {

  @Override
  public ImageInterface process(ImageInterface img) throws IllegalArgumentException {
    return ImageFilter.filter(Kernel.gaussianBlur(), img);
  }

  @Override
  protected double[][] getKernel() {
    return Kernel.gaussianBlur();
  }
}
