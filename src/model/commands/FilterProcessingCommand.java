package model.commands;

import controller.ImageFilter;
import model.ImageInterface;

public abstract class FilterProcessingCommand implements ImageProcessingCommand {

  @Override
  public ImageInterface process(ImageInterface img) throws IllegalArgumentException {
    return ImageFilter.filter(this.getKernel(), img);
  }

  abstract protected double[][] getKernel();
}
