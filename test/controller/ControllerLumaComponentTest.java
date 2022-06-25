package controller;

import org.junit.Before;
import org.junit.Test;

import controller.loader.ImageLoader;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for luma-component command for controller.
 */
public class ControllerLumaComponentTest {
  private ImageProcessingModel model;

  @Before
  public void init() {
    this.model = new ImageProcessingModelImpl();
  }

  @Test
  public void testValid() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load res/test3x4.ppm test"),
            IPCTester.prints("Successfully stored res/test3x4.ppm as: test."),
            IPCTester.inputs("luma-component test test-luma"),
            IPCTester.prints("Successfully applied luma component and stored as: test-luma."),
            IPCTester.inputs("q"),
            IPCTester.prints("Quitting Image Processing...")));
    assertEquals(ImageLoader.load("res/test-luma.ppm"),
            this.model.getImage("test-luma"));
  }

  @Test
  public void testImageDoesNotExist() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load res/test3x4.ppm test"),
            IPCTester.prints("Successfully stored res/test3x4.ppm as: test."),
            IPCTester.inputs("luma-component does-not-exist test-luma"),
            IPCTester.prints("Image not found."),
            IPCTester.inputs("q"),
            IPCTester.prints("Quitting Image Processing...")));
  }

  @Test
  public void testInvalid() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load res/test3x4.ppm test"),
            IPCTester.prints("Successfully stored res/test3x4.ppm as: test."),
            IPCTester.inputs("luma-component 1 2"),
            IPCTester.prints("Image not found."),
            IPCTester.inputs("q"),
            IPCTester.prints("Quitting Image Processing...")));
  }

  @Test
  public void testLessArgs() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load res/test3x4.ppm test"),
            IPCTester.prints("Successfully stored res/test3x4.ppm as: test."),
            IPCTester.inputs("luma-component test"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q"),
            IPCTester.prints("Quitting Image Processing...")));
  }

  @Test
  public void testMoreArgs() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load res/test3x4.ppm test"),
            IPCTester.prints("Successfully stored res/test3x4.ppm as: test."),
            IPCTester.inputs("luma-component test test-luma extra"),
            IPCTester.prints("Failed to apply mask."),
            IPCTester.inputs("q"),
            IPCTester.prints("Quitting Image Processing...")));
  }
}
