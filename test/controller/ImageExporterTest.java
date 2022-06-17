package controller;

import controller.exporter.ImageExporter;
import controller.loader.ImageLoader;
import model.ImageInterface;

import org.junit.Test;

import model.commands.FlipVerticalCommand;
import model.commands.ImageProcessingCommand;

import static org.junit.Assert.*;

/**
 * Tests the {@code ImageExporter} class.
 */
public class ImageExporterTest {

  private boolean testExport(String extension) {
    ImageInterface unprocessedImage = ImageLoader.load("res/test3x4." + extension);
    ImageProcessingCommand cmd = new FlipVerticalCommand();
    ImageInterface processedImage = cmd.process(unprocessedImage);
    String exportFilePath = "res/test-vertical." + extension;
    try {
      ImageExporter.export(processedImage, exportFilePath);
      ImageInterface exportedImage = ImageLoader.load(exportFilePath);
      assertEquals(processedImage, exportedImage);
    } catch (IllegalArgumentException e) {
      // If export does not work, fail test.
      fail(e.getMessage());
    }
    return true;
  }

  @Test
  public void testExportPPM() {
    assertTrue(this.testExport("ppm"));
  }

  @Test
  public void testExportBMP() {
    assertTrue(this.testExport("bmp"));
  }

  @Test
  public void testExportPNG() {
    assertTrue(this.testExport("png"));
  }

  @Test
  public void testExportTransparentPNG() {
    ImageInterface unprocessedImage = ImageLoader.load("res/mario.png");
    ImageProcessingCommand cmd = new FlipVerticalCommand();
    ImageInterface processedImage = cmd.process(unprocessedImage);
    String exportFilePath = "res/mario-vertical.png";
    try {
      ImageExporter.export(processedImage, exportFilePath);
      ImageInterface exportedImage = ImageLoader.load(exportFilePath);
      assertEquals(processedImage, exportedImage);
    } catch (IllegalArgumentException e) {
      // If export does not work, fail test.
      fail(e.getMessage());
    }
  }

  @Test
  public void testExportJPEG() {
    try {
      ImageInterface loadedImage = ImageLoader.load("res/test3x4.jpeg");
      ImageProcessingCommand cmd = new FlipVerticalCommand();
      ImageInterface processedImage = cmd.process(loadedImage);
      ImageExporter.export(processedImage, "res/test-vertical.jpeg");
    } catch (IllegalArgumentException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void testExportJPG() {
    try {
      ImageInterface loadedImage = ImageLoader.load("res/test3x4.jpg");
      ImageProcessingCommand cmd = new FlipVerticalCommand();
      ImageInterface processedImage = cmd.process(loadedImage);
      ImageExporter.export(processedImage, "res/test-vertical.jpg");
    } catch (IllegalArgumentException e) {
      fail(e.getMessage());
    }
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