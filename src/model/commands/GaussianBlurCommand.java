package model.commands;

import model.Image;
import controller.ImageFilter;
import controller.Kernel;

public class GaussianBlurCommand extends FilterProcessingCommand implements ImageProcessingCommand {

  @Override
  public Image process(Image img) throws IllegalArgumentException {
    return ImageFilter.filter(Kernel.gaussianBlur(), img);
  }

  @Override
  protected double[][] getKernel() {
    return Kernel.gaussianBlur();
  }
}
