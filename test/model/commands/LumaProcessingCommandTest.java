package model.commands;

import model.ImageInterface;
import model.Pixel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@code LumaProcessingCommand}.
 */
public class LumaProcessingCommandTest extends AbstractProcessingCommandTest {

  @Test
  public void process() {
    ImageProcessingCommand cmd = new LumaProcessingCommand();
    ImageInterface processedImage = cmd.process(this.unprocessedImage);
    for (int i = 0; i < processedImage.getHeight(); i++) {
      for (int j = 0; j < processedImage.getWidth(); j++) {
        Pixel unprocessedPx = this.unprocessedImage.getPixelAt(i, j);
        Pixel processedPx = processedImage.getPixelAt(i, j);
        int luma = (int) (0.2126 * unprocessedPx.getRed()
                + 0.7152 * unprocessedPx.getGreen()
                + 0.0722 * unprocessedPx.getBlue());
        assertEquals(luma, processedPx.getRed());
        assertEquals(luma, processedPx.getGreen());
        assertEquals(luma, processedPx.getBlue());
      }
    }
  }
}