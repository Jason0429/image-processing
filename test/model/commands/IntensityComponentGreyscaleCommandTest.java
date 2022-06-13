package model.commands;

import controller.ImageLoader;
import model.ImageInterface;
import model.Pixel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Testing for {@code IntensityComponentGreyscaleCommand}.
 */
public class IntensityComponentGreyscaleCommandTest {
  private ImageInterface unprocessedImage;

  @Before
  public void init() {
    this.unprocessedImage = ImageLoader.load("res/test3x4.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void processNullImage() {
    ImageProcessingCommand cmd = new IntensityComponentGreyscaleCommand();
    ImageInterface processedImage = cmd.process(null);
  }

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
        assertEquals(intensity, processedPx.getRed());
        assertEquals(intensity, processedPx.getGreen());
        assertEquals(intensity, processedPx.getBlue());
      }
    }
  }
}