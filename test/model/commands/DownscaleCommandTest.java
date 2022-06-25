package model.commands;

import model.ImageInterface;
import model.Pixel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DownscaleCommandTest extends AbstractProcessingCommandTest {

  @Test
  public void process() {
    ImageProcessingCommand cmd = new DownscaleCommand(2, 2);
    ImageInterface processedImage = cmd.process(this.unprocessedImage);
    assertEquals(2, processedImage.getWidth());
    assertEquals(2, processedImage.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDimensions() {
    ImageProcessingCommand cmd = new DownscaleCommand(4, 4);
    ImageInterface processedImage = cmd.process(this.unprocessedImage);
  }
}
