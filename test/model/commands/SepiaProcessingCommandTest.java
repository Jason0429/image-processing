package model.commands;

import model.ImageInterface;
import model.Pixel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SepiaProcessingCommandTest extends AbstractProcessingCommandTest {

  @Test
  public void process() {
    ImageProcessingCommand cmd = new SepiaProcessingCommand();
    ImageInterface processedImage = cmd.process(this.unprocessedImage);
    for (int i = 0; i < processedImage.getHeight(); i++) {
      for (int j = 0; j < processedImage.getWidth(); j++) {
        Pixel unprocessedPx = this.unprocessedImage.getPixelAt(i, j);
        Pixel processedPx = processedImage.getPixelAt(i, j);
        int sepiaRed = (int) (0.393 * unprocessedPx.getRed()
                + 0.769 * unprocessedPx.getGreen()
                + 0.189 * unprocessedPx.getBlue());
        int sepiaGreen = (int) (0.349 * unprocessedPx.getRed()
                + 0.686 * unprocessedPx.getGreen()
                + 0.168 * unprocessedPx.getBlue());
        int sepiaBlue = (int) (0.272 * unprocessedPx.getRed()
                + 0.534 * unprocessedPx.getGreen()
                + 0.131 * unprocessedPx.getBlue());

        assertEquals(Math.max(0, Math.min(unprocessedPx.getMaxValue(), sepiaRed)),
                processedPx.getRed());
        assertEquals(Math.max(0, Math.min(unprocessedPx.getMaxValue(), sepiaGreen)),
                processedPx.getGreen());
        assertEquals(Math.max(0, Math.min(unprocessedPx.getMaxValue(), sepiaBlue)),
                processedPx.getBlue());
      }
    }
  }
}
