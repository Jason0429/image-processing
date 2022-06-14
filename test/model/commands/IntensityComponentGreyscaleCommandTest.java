package model.commands;

import model.ImageInterface;
import model.Pixel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Testing for {@code IntensityComponentGreyscaleCommand}.
 */
public class IntensityComponentGreyscaleCommandTest extends AbstractProcessingCommandTest {

  @Test
  public void process() {
    ImageProcessingCommand cmd = new IntensityComponentGreyscaleCommand();
    ImageInterface processedImage = cmd.process(this.unprocessedImage);
    for (int i = 0; i < processedImage.getHeight(); i++) {
      for (int j = 0; j < processedImage.getWidth(); j++) {
        Pixel unprocessedPx = this.unprocessedImage.getPixelAt(i, j);
        Pixel processedPx = processedImage.getPixelAt(i, j);
        int intensity = (int) ((unprocessedPx.getRed()
                + unprocessedPx.getGreen()
                + unprocessedPx.getBlue()) / 3.0);
        intensity = Math.max(0, Math.min(unprocessedPx.getMaxValue(), intensity));
        assertEquals(intensity, processedPx.getRed());
        assertEquals(intensity, processedPx.getGreen());
        assertEquals(intensity, processedPx.getBlue());
      }
    }
  }
}