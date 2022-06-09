package model.commands;

import org.junit.Before;
import org.junit.Test;

import controller.ImageLoader;
import model.Image;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@code FlipVerticalCommand}.
 */
public class FlipVerticalCommandTest {
  private Image unprocessedImage;

  @Before
  public void init() {
    this.unprocessedImage = ImageLoader.load("res/test3x4.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void processNullImage() {
    ImageProcessingCommand cmd = new FlipVerticalCommand();
    Image processedImage = cmd.process(null);
  }

  @Test
  public void process() {
    ImageProcessingCommand cmd = new FlipVerticalCommand();
    Image processedImage = cmd.process(this.unprocessedImage);
    for (int i = 0; i < this.unprocessedImage.getHeight(); i++) {
      for (int j = 0; j < this.unprocessedImage.getWidth(); j++) {
        Pixel unprocessedPx = this.unprocessedImage.getPixelAt(i, j);
        Pixel processedPx = processedImage.getPixelAt(
                this.unprocessedImage.getHeight() - i - 1, j);
        assertEquals(processedPx, unprocessedPx);
      }
    }
  }
}