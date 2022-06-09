package controller;

import org.junit.Before;
import org.junit.Test;

import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests load command in controller.
 */
public class ControllerLoadTest {
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
            IPCTester.prints("Successfully loaded test from res/test3x4.ppm"),
            IPCTester.inputs("q")));
    assertEquals(ImageLoader.load("res/test3x4.ppm"),
            this.model.getImage("test"));
  }

  @Test
  public void testInvalid() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/invalid.ppm invalid"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q")));
  }

  @Test
  public void testInvalidWithInts() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load 1 23"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q")));
  }

  @Test
  public void testLessArgs() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load res/test3x4.ppm"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q")));
  }

  @Test
  public void testMoreArgs() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load res/test3x4.ppm test extra"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q")));
  }
}
