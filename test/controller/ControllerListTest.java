package controller;

import org.junit.Before;
import org.junit.Test;

import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;

import static org.junit.Assert.assertTrue;

/**
 * Tests for list command in controller.
 */
public class ControllerListTest {
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
            IPCTester.inputs("list"),
            IPCTester.prints("List of images stored:"),
            IPCTester.prints("test"),
            IPCTester.inputs("q"),
            IPCTester.prints("Quitting Image Processing...")));
  }


  @Test
  public void testValidMultipleImages() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load res/test3x4.ppm test"),
            IPCTester.prints("Successfully stored res/test3x4.ppm as: test."),
            IPCTester.inputs("load res/test3x4.ppm test1"),
            IPCTester.prints("Successfully stored res/test3x4.ppm as: test1."),
            IPCTester.inputs("list"),
            IPCTester.prints("List of images stored:"),
            IPCTester.prints("test\ntest1"),
            IPCTester.inputs("q"),
            IPCTester.prints("Quitting Image Processing...")));
  }

  @Test
  public void testEmptyList() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("list"),
            IPCTester.prints("No images stored."),
            IPCTester.inputs("q"),
            IPCTester.prints("Quitting Image Processing...")));
  }
}
