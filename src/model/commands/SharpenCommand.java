package model.commands;

import controller.ImageFilter;
import model.ImageInterface;

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
