package controller;

import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for save command in controller.
 */
public class ControllerSaveTest {
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
            IPCTester.inputs("load test/test-images/test3x4.ppm test"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("vertical-flip test test-vertical"),
            IPCTester.prints(
                    "Successfully flipped image vertically and stored as: test-vertical"),
            IPCTester.inputs(
                    "save test/test-images/test-vertical.ppm test-vertical"),
            IPCTester.prints("Successfully saved test-vertical at " +
                    "test/test-images/test-vertical.ppm"),
            IPCTester.inputs("q")));
    assertEquals(ImageLoader.load("./test/test-images/test-vertical.ppm"),
            this.model.getImage("test-vertical"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImageNotFound() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/test-images/test3x4.ppm test"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("vertical-flip test test-vertical"),
            IPCTester.prints(
                    "Successfully flipped image vertically and stored as: test-vertical"),
            IPCTester.inputs(
                    "save test/test-images/test-vertical.ppm does-not-exist"),
            IPCTester.inputs("q")));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalid() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/test-images/test3x4.ppm test"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("vertical-flip test test-vertical"),
            IPCTester.prints(
                    "Successfully flipped image vertically and stored as: test-vertical"),
            IPCTester.inputs(
                    "save 1 2"),
            IPCTester.inputs("q")));
  }

  @Test
  public void testLessArgs() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/test-images/test3x4.ppm test"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("vertical-flip test test-vertical"),
            IPCTester.prints(
                    "Successfully flipped image vertically and stored as: test-vertical"),
            IPCTester.inputs(
                    "save test/test-images/test3x4-vertical.ppm"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q")));
  }

  @Test
  public void testMoreArgs() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/test-images/test3x4.ppm test"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("vertical-flip test test-vertical"),
            IPCTester.prints(
                    "Successfully flipped image vertically and stored as: test-vertical"),
            IPCTester.inputs(
                    "save test/test-images/test-vertical.ppm test-vertical-flip extra"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q")));
  }
}
