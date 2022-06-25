package controller;

import org.junit.Before;
import org.junit.Test;

import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;

import static org.junit.Assert.assertTrue;

/**
 * Tests brighten command for controller.
 */
public class ControllerBrightenTest {
  private ImageProcessingModel model;

  @Before
  public void init() {
    this.model = new ImageProcessingModelImpl();
  }

  @Test
  public void testValidBrighten() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load res/test3x4.ppm test"),
            IPCTester.prints("Successfully stored res/test3x4.ppm as: test."),
            IPCTester.inputs("brighten test test-brighten-10 10"),
            IPCTester.prints("Successfully brightened image and stored as: test-brighten-10."),
            IPCTester.inputs("q"),
            IPCTester.prints("Quitting Image Processing...")));
  }

  @Test
  public void testValidDarken() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load res/test3x4.ppm test"),
            IPCTester.prints("Successfully stored res/test3x4.ppm as: test."),
            IPCTester.inputs("brighten test test-darken-10 -10"),
            IPCTester.prints("Successfully brightened image and stored as: test-darken-10."),
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
            IPCTester.inputs("brighten does-not-exist test-brighten-10 10"),
            IPCTester.prints("Image not found."),
            IPCTester.inputs("q"),
            IPCTester.prints("Quitting Image Processing...")));
  }

  @Test
  public void testInvalidArgs() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load res/test3x4.ppm test"),
            IPCTester.prints("Successfully stored res/test3x4.ppm as: test."),
            IPCTester.inputs("brighten -h la 1"),
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
            IPCTester.inputs("brighten 1"),
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
            IPCTester.inputs("brighten test test-brighten-10 extra 10"),
            IPCTester.prints("Failed to apply mask."),
            IPCTester.inputs("q"),
            IPCTester.prints("Quitting Image Processing...")));
  }
}
