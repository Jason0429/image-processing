package model.commands;

import model.Image;
import model.ImageFilter;
import model.Kernel;

public class GaussianBlurCommand implements ImageProcessingCommand {

  @Override
  public Image process(Image img) throws IllegalArgumentException {
    return ImageFilter.filter(Kernel.gaussianBlur(), img);
  }
}
