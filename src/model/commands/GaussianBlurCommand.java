package model.commands;

import model.Image;
import controller.ImageFilter;
import controller.query.Kernel;
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
