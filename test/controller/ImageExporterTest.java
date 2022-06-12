package controller;

import model.ImageInterface;
import org.junit.Test;

import java.io.IOException;

import model.Image;
import model.commands.FlipVerticalCommand;
import model.commands.ImageProcessingCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the {@code ImageExporter} class.
 */
public class ImageExporterTest {

  @Test
  public void export() {
    ImageInterface unprocessedImage = ImageLoader.load("res/test3x4.ppm");
    ImageProcessingCommand cmd = new FlipVerticalCommand();
    ImageInterface processedImage = cmd.process(unprocessedImage);
    String exportFilePath = "res/test-vertical.ppm";
    try {
      ImageExporter.export(processedImage, exportFilePath);
      ImageInterface exportedImage = ImageLoader.load(exportFilePath);
      assertEquals(processedImage, exportedImage);
    } catch (IOException e) {
      // If export does not work, fail test.
      fail("Export failed");
    }
  }
}