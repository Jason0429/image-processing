package controller;

import org.junit.Before;
import org.junit.Test;

import controller.loader.ImageLoader;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests horizontal-component for controller.
 */
public class ControllerHorizontalFlipTest {
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
            IPCTester.inputs("horizontal-flip test test-horizontal"),
            IPCTester.prints(
                    "Successfully flipped image horizontally and stored as: test-horizontal."),
            IPCTester.inputs("q"),
            IPCTester.prints("Quitting Image Processing...")));
    assertEquals(ImageLoader.load("res/test-horizontal.ppm"),
            this.model.getImage("test-horizontal"));
  }

  @Test
  public void testImageDoesNotExist() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load res/test3x4.ppm test"),
            IPCTester.prints("Successfully stored res/test3x4.ppm as: test."),
            IPCTester.inputs("horizontal-flip does-not-exist test-horizontal"),
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
            IPCTester.inputs("horizontal-flip 1 2"),
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
            IPCTester.inputs("horizontal-flip test"),
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
            IPCTester.inputs("horizontal-flip test test-horizontal extra"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q"),
            IPCTester.prints("Quitting Image Processing...")));
  }
}
