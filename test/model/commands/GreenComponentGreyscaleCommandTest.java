package model.commands;

import model.ImageInterface;

import org.junit.Test;

import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Testing for {@code GreenComponentGreyscaleCommand}.
 */
public class GreenComponentGreyscaleCommandTest extends AbstractProcessingCommandTest {

  @Test
  public void process() {
    ImageProcessingCommand cmd = new GreenComponentGreyscaleCommand();
    ImageInterface processedImage = cmd.process(this.unprocessedImage);
    for (int i = 0; i < processedImage.getHeight(); i++) {
      for (int j = 0; j < processedImage.getWidth(); j++) {
        Pixel px = processedImage.getPixelAt(i, j);
        assertEquals(px.getGreen(), px.getRed());
        assertEquals(px.getGreen(), px.getBlue());
      }
    }
  }
}