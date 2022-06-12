package model.commands;

import controller.ImageFilter;
import model.ImageInterface;

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
