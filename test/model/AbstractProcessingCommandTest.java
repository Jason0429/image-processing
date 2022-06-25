package model;

import controller.loader.ImageLoader;
import model.commands.BlueComponentGreyscaleCommand;
import model.commands.ImageProcessingCommand;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the common methods in the commands.
 */
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
