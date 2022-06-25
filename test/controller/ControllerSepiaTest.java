package controller;

import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Tests for sepia command in controller.
 */
public class ControllerSepiaTest {
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
            IPCTester.inputs("sepia test test-sepia"),
            IPCTester.prints("Successfully applied sepia and stored as: test-sepia."),
            IPCTester.inputs("q"),
            IPCTester.prints("Quitting Image Processing...")));
  }

  @Test
  public void testImageDoesNotExist() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load res/test3x4.ppm test"),
            IPCTester.prints("Successfully stored res/test3x4.ppm as: test."),
            IPCTester.inputs("sepia does-not-exist test-sepia"),
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
            IPCTester.inputs("sepia 1 2"),
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
            IPCTester.inputs("sepia test"),
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
            IPCTester.inputs("sepia test test-sepia extra"),
            IPCTester.prints("Failed to apply mask."),
            IPCTester.inputs("q"),
            IPCTester.prints("Quitting Image Processing...")));
  }
}
