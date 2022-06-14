package model.commands;

import model.ImageInterface;

import org.junit.Test;

import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Testing for {@code RedComponentGreyscaleCommand}.
 */
public class RedComponentGreyscaleCommandTest extends AbstractProcessingCommandTest {

  @Test
  public void process() {
    ImageProcessingCommand cmd = new RedComponentGreyscaleCommand();
    ImageInterface processedImage = cmd.process(this.unprocessedImage);
    for (int i = 0; i < processedImage.getHeight(); i++) {
      for (int j = 0; j < processedImage.getWidth(); j++) {
        Pixel px = processedImage.getPixelAt(i, j);
        assertEquals(px.getRed(), px.getBlue());
        assertEquals(px.getRed(), px.getGreen());
      }
    }
  }
}