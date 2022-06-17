package model.commands;

import model.ImageInterface;
import model.Pixel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the sharpen command.
 */
public class SharpenCommandTest extends AbstractProcessingCommandTest {

  @Test
  public void process() {
    ImageProcessingCommand cmd = new SharpenCommand();
    ImageInterface processedImage = cmd.process(this.unprocessedImage);

    Pixel[][] pixelArray = new Pixel[4][3];
    pixelArray[0] = new Pixel[]{
            new Pixel(255, 255, 255, 4),

            new Pixel(255, 255, 255, 5),
            new Pixel(255, 181, 255, 3)};
    pixelArray[1] = new Pixel[]{
            new Pixel(255, 255, 255, 5),
            new Pixel(255, 255, 255, 8),
            new Pixel(255, 255, 255, 4)};
    pixelArray[2] = new Pixel[]{
            new Pixel(255, 255, 255, 5),
            new Pixel(255, 255, 255, 7),
            new Pixel(255, 255, 255, 4)};
    pixelArray[3] = new Pixel[]{
            new Pixel(255, 155, 126, 3),
            new Pixel(255, 255, 233, 5),
            new Pixel(255, 206, 125, 3)};

    for (int i = 0; i < processedImage.getHeight(); i++) {
      for (int j = 0; j < processedImage.getWidth(); j++) {
        Pixel cP = processedImage.getPixelAt(i, j);
        assertEquals(pixelArray[i][j], cP);
      }
    }
  }
}
