package model.commands;

import model.ImageInterface;
import org.junit.Before;
import org.junit.Test;

import controller.ImageLoader;
import model.Image;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Testing for {@code BlueComponentGreyscaleCommand}.
 */
public class BlueComponentGreyscaleCommandTest extends AbstractProcessingCommandTest {

  @Test
  public void process() {
    ImageProcessingCommand cmd = new BlueComponentGreyscaleCommand();
    ImageInterface processedImage = cmd.process(this.unprocessedImage);
    for (int i = 0; i < processedImage.getHeight(); i++) {
      for (int j = 0; j < processedImage.getWidth(); j++) {
        Pixel px = processedImage.getPixelAt(i, j);
        assertEquals(px.getBlue(), px.getRed());
        assertEquals(px.getBlue(), px.getGreen());
      }
    }
  }
}