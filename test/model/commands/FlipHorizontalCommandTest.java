package model.commands;

import model.ImageInterface;
import org.junit.Before;
import org.junit.Test;

import controller.ImageLoader;
import model.Image;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@code FlipHorizontalCommand}.
 */
public class FlipHorizontalCommandTest {
  private ImageInterface unprocessedImage;

  @Before
  public void init() {
    this.unprocessedImage = ImageLoader.load("res/test3x4.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void processNullImage() {
    ImageProcessingCommand cmd = new FlipHorizontalCommand();
    ImageInterface processedImage = cmd.process(null);
  }

  @Test
  public void process() {
    ImageProcessingCommand cmd = new FlipHorizontalCommand();
    ImageInterface processedImage = cmd.process(this.unprocessedImage);
    for (int i = 0; i < this.unprocessedImage.getHeight(); i++) {
      for (int j = 0; j < this.unprocessedImage.getWidth(); j++) {
        Pixel unprocessedPx = this.unprocessedImage.getPixelAt(i, j);
        Pixel processedPx = processedImage.getPixelAt(i,
                this.unprocessedImage.getWidth() - j - 1);
        assertEquals(processedPx, unprocessedPx);
      }
    }
  }
}