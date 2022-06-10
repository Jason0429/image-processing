package model.commands;

import model.Image;
import controller.ImageFilter;
import controller.Kernel;

public class SharpenCommand implements ImageProcessingCommand {

  @Override
  public Image process(Image img) throws IllegalArgumentException {
    return ImageFilter.filter(Kernel.sharpen(), img);
  }
}
