package model.commands;

import model.ImageInterface;
import model.Pixel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the gaussian blur command.
 */
public class GaussianBlurCommandTest extends AbstractProcessingCommandTest {

  @Test
  public void process() {
    ImageProcessingCommand cmd = new GaussianBlurCommand();
    ImageInterface processedImage = cmd.process(this.unprocessedImage);

    Pixel[][] pixelArray = new Pixel[4][3];
    pixelArray[0] = new Pixel[]{
      new Pixel(255, 141, 112, 1),
      new Pixel(255, 171, 167, 2),
      new Pixel(255, 107, 137, 1)};
    pixelArray[1] = new Pixel[]{
      new Pixel(255, 189, 149, 2),
      new Pixel(255, 229, 223, 3),
      new Pixel(255, 143, 182, 2)};
    pixelArray[2] = new Pixel[]{
      new Pixel(255, 163, 131, 2),
      new Pixel(255, 216, 192, 2),
      new Pixel(255, 143, 155, 2)};
    pixelArray[3] = new Pixel[]{
      new Pixel(255, 91, 74, 1),
      new Pixel(255, 146, 105, 2),
      new Pixel(255, 107, 83, 1)};

    for (int i = 0; i < processedImage.getHeight(); i++) {
      for (int j = 0; j < processedImage.getWidth(); j++) {
        Pixel cP = processedImage.getPixelAt(i, j);
        assertEquals(pixelArray[i][j], cP);
      }
    }
  }
}
