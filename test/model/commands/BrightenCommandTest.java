package model.commands;

import model.ImageInterface;

import org.junit.Test;

import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Testing for {@code BrightenCommand}.
 */
public class BrightenCommandTest extends AbstractProcessingCommandTest {

  @Test
  public void testBrightenProcess() {
    int increment = 10;
    ImageProcessingCommand cmd = new BrightenCommand(increment);
    ImageInterface processedImage = cmd.process(this.unprocessedImage);
    for (int i = 0; i < processedImage.getHeight(); i++) {
      for (int j = 0; j < processedImage.getWidth(); j++) {
        Pixel unprocessedPx = this.unprocessedImage.getPixelAt(i, j);
        Pixel processedPx = processedImage.getPixelAt(i, j);
        assertEquals(
                Math.min(unprocessedPx.getRed() + increment, unprocessedPx.getMaxValue()),
                processedPx.getRed());
        assertEquals(
                Math.min(unprocessedPx.getGreen() + increment, unprocessedPx.getMaxValue()),
                processedPx.getGreen());
        assertEquals(
                Math.min(unprocessedPx.getBlue() + increment, unprocessedPx.getMaxValue()),
                processedPx.getBlue());
      }
    }
  }

  @Test
  public void testDarkenProcess() {
    int decrement = 10;
    ImageProcessingCommand cmd = new BrightenCommand(-decrement);
    ImageInterface processedImage = cmd.process(this.unprocessedImage);
    for (int i = 0; i < processedImage.getHeight(); i++) {
      for (int j = 0; j < processedImage.getWidth(); j++) {
        Pixel unprocessedPx = this.unprocessedImage.getPixelAt(i, j);
        Pixel processedPx = processedImage.getPixelAt(i, j);
        int oldRed = unprocessedPx.getRed();
        int oldGreen = unprocessedPx.getGreen();
        int oldBlue = unprocessedPx.getBlue();
        assertEquals(
                Math.max(unprocessedPx.getRed() - decrement, 0),
                processedPx.getRed());
        assertEquals(
                Math.max(unprocessedPx.getGreen() - decrement, 0),
                processedPx.getGreen());
        assertEquals(
                Math.max(unprocessedPx.getBlue() - decrement, 0),
                processedPx.getBlue());
      }
    }
  }
}