package model;

import org.junit.Test;

import controller.loader.ImageLoader;
import model.commands.DownscaleCommand;
import model.commands.ImageMaskCommand;
import model.commands.ImageProcessingCommand;
import model.commands.SepiaProcessingCommand;

import static org.junit.Assert.assertEquals;

public class ImageMaskCommandTest {
  @Test
  public void process() {
    ImageInterface maskImage = ImageLoader.load("res/mario-head-mask.png");
    ImageInterface sourceImage = ImageLoader.load("res/mario.png");
    ImageInterface successfulMaskedImage = ImageLoader.load("res/mario-sepia-masked.png");
    ImageProcessingCommand cmd = new ImageMaskCommand(maskImage, new SepiaProcessingCommand());
    ImageInterface processedImage = cmd.process(sourceImage);
    assertEquals(processedImage, successfulMaskedImage);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullMaskAndCommand() {
    ImageProcessingCommand cmd = new ImageMaskCommand(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullMask() {
    ImageProcessingCommand cmd = new ImageMaskCommand(null, new SepiaProcessingCommand());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullCommand() {
    ImageInterface maskImage = ImageLoader.load("res/mario-head-mask.png");
    ImageProcessingCommand cmd = new ImageMaskCommand(maskImage, null);
  }
}
