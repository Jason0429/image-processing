package controller;

import controller.exporter.ImageExporter;
import controller.loader.ImageLoader;
import model.ExceptionMessage;
import model.ImageInterface;

import org.junit.Test;

import java.io.IOException;

import model.commands.FlipVerticalCommand;
import model.commands.ImageProcessingCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the {@code ImageExporter} class.
 */
public class ImageExporterTest {

  private void testExport(String extension) {
    ImageInterface unprocessedImage = ImageLoader.load("res/test3x4.ppm");
    ImageProcessingCommand cmd = new FlipVerticalCommand();
    ImageInterface processedImage = cmd.process(unprocessedImage);
    String exportFilePath = "res/test-vertical." + extension;
    try {
      ImageExporter.export(processedImage, exportFilePath);
      ImageInterface exportedImage = ImageLoader.load(exportFilePath);
      assertEquals(processedImage, exportedImage);
    } catch (IllegalArgumentException e) {
      // If export does not work, fail test.
      fail("Export failed");
    }
  }

  @Test
  public void testExportPPM() {
    this.testExport(".ppm");
  }

  @Test
  public void testExportBMP() {
    this.testExport("bmp");
  }

  @Test
  public void testExportPNG() {
    this.testExport(".png");
  }

  @Test
  public void testExportJPEG() {
    this.testExport(".jpeg");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExportUnsupported() {
    ImageInterface unprocessedImage = ImageLoader.load("res/test3x4.ppm");
    ImageProcessingCommand cmd = new FlipVerticalCommand();
    ImageInterface processedImage = cmd.process(unprocessedImage);
    String exportFilePath = "res/test-vertical.docx";
    ImageExporter.export(processedImage, exportFilePath);
    ImageInterface exportedImage = ImageLoader.load(exportFilePath);
    assertEquals(processedImage, exportedImage);
  }
}