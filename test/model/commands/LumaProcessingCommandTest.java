package model.commands;

import controller.ImageLoader;
import model.ImageInterface;
import model.Pixel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@code LumaProcessingCommand}.
 */
public class LumaProcessingCommandTest {
  private ImageInterface unprocessedImage;

  @Before
  public void init() {
    this.unprocessedImage = ImageLoader.load("res/test3x4.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void processNullImage() {
    ImageProcessingCommand cmd = new LumaProcessingCommand();
    ImageInterface processedImage = cmd.process(null);
  }

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