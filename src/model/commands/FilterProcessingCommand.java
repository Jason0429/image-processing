package model.commands;

import controller.ImageFilter;
import model.Image;

public abstract class FilterProcessingCommand implements ImageProcessingCommand {

  @Override
  public Image process(Image img) throws IllegalArgumentException {
    return ImageFilter.filter(this.getKernel(), img);
  }

  abstract protected double[][] getKernel();
}
