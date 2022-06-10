package model.commands;

import model.Image;
import model.ImageFilter;
import model.Kernel;

public class SharpenCommand implements ImageProcessingCommand {

  @Override
  public Image process(Image img) throws IllegalArgumentException {
    return ImageFilter.filter(Kernel.sharpen(), img);
  }
}
