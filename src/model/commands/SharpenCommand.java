package model.commands;

import model.Image;
import controller.ImageFilter;
import controller.query.Kernel;

public class SharpenCommand extends FilterProcessingCommand implements ImageProcessingCommand {

  @Override
  public Image process(Image img) throws IllegalArgumentException {
    return ImageFilter.filter(Kernel.sharpen(), img);
  }

  @Override
  protected double[][] getKernel() {
    return Kernel.sharpen();
  }
}
