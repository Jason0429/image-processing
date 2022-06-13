package model.commands;

import controller.ImageLoader;
import model.ImageInterface;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractProcessingCommandTest {
  protected ImageInterface unprocessedImage;

  @Before
  public void init() {
    this.unprocessedImage = ImageLoader.load("res/test3x4.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void processNullImage() {
    ImageProcessingCommand cmd = new BlueComponentGreyscaleCommand();
    ImageInterface processedImage = cmd.process(null);
  }
}
