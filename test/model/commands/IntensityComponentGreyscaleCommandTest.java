package model.commands;

import org.junit.Before;
import org.junit.Test;

import controller.ImageLoader;
import model.Image;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Testing for {@code IntensityComponentGreyscaleCommand}.
 */
public class IntensityComponentGreyscaleCommandTest {
  private Image unprocessedImage;

  @Before
  public void init() {
    this.unprocessedImage = ImageLoader.load("test/test-images/test3x4.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void processNullImage() {
    ImageProcessingCommand cmd = new IntensityComponentGreyscaleCommand();
    Image processedImage = cmd.process(null);
  }

  @Test
  public void process() {
    ImageProcessingCommand cmd = new IntensityComponentGreyscaleCommand();
    Image processedImage = cmd.process(this.unprocessedImage);
    for (int i = 0; i < processedImage.getHeight(); i++) {
      for (int j = 0; j < processedImage.getWidth(); j++) {
        Pixel unprocessedPx = this.unprocessedImage.getPixelAt(i, j);
        Pixel processedPx = processedImage.getPixelAt(i, j);
        assertEquals((int) unprocessedPx.getIntensity(), processedPx.getRed());
        assertEquals((int) unprocessedPx.getIntensity(), processedPx.getGreen());
        assertEquals((int) unprocessedPx.getIntensity(), processedPx.getBlue());
      }
    }
  }
}