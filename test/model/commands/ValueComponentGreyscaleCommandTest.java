package model.commands;

import model.ImageInterface;

import org.junit.Before;
import org.junit.Test;

import controller.loader.ImageLoader;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@code ValueComponentGreyscaleCommand}.
 */
public class ValueComponentGreyscaleCommandTest {
  private ImageInterface unprocessedImage;

  @Before
  public void init() {
    this.unprocessedImage = ImageLoader.load("res/test3x4.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void processNullImage() {
    ImageProcessingCommand cmd = new ValueComponentGreyscaleCommand();
    ImageInterface processedImage = cmd.process(null);
  }

  @Test
  public void process() {
    ImageProcessingCommand cmd = new ValueComponentGreyscaleCommand();
    ImageInterface processedImage = cmd.process(this.unprocessedImage);
    for (int i = 0; i < processedImage.getHeight(); i++) {
      for (int j = 0; j < processedImage.getWidth(); j++) {
        Pixel unprocessedPx = this.unprocessedImage.getPixelAt(i, j);
        Pixel processedPx = processedImage.getPixelAt(i, j);
        int maxValue = Math.max(unprocessedPx.getRed(), Math.max(unprocessedPx.getGreen(),
                unprocessedPx.getBlue()));
        assertEquals(maxValue, processedPx.getRed());
        assertEquals(maxValue, processedPx.getGreen());
        assertEquals(maxValue, processedPx.getBlue());
      }
    }
  }
}