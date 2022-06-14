package model.commands;

import model.ImageInterface;

import org.junit.Test;

import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@code FlipVerticalCommand}.
 */
public class FlipVerticalCommandTest extends AbstractProcessingCommandTest {

  @Test
  public void process() {
    ImageProcessingCommand cmd = new FlipVerticalCommand();
    ImageInterface processedImage = cmd.process(this.unprocessedImage);
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