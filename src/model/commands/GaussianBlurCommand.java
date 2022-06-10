package model.commands;

import model.Image;

public class GaussianBlurCommand implements ImageProcessingCommand {

  @Override
  public Image process(Image img) throws IllegalArgumentException {
    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {

      }
    }
    return null;
  }
}
