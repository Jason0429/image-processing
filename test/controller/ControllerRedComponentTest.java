package controller;

import org.junit.Before;
import org.junit.Test;

import controller.loader.ImageLoader;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for red-component command in controller.
 */
public class ControllerRedComponentTest {
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
            IPCTester.inputs("red-component test test-red"),
            IPCTester.prints("Successfully applied red component and stored as: test-red"),
            IPCTester.inputs("q")));
    assertEquals(ImageLoader.load("res/test-red.ppm"),
            this.model.getImage("test-red"));
  }

  @Test
  public void testImageDoesNotExist() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load res/test3x4.ppm test"),
            IPCTester.prints("Successfully loaded test from res/test3x4.ppm"),
            IPCTester.inputs("red-component does-not-exist test-red"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q")));
  }

  @Test
  public void testInvalid() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load res/test3x4.ppm test"),
            IPCTester.prints("Successfully loaded test from res/test3x4.ppm"),
            IPCTester.inputs("red-component 1 2"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q")));
  }

  @Test
  public void testLessArgs() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load res/test3x4.ppm test"),
            IPCTester.prints("Successfully loaded test from res/test3x4.ppm"),
            IPCTester.inputs("red-component test"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q")));
  }

  @Test
  public void testMoreArgs() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load res/test3x4.ppm test"),
            IPCTester.prints("Successfully loaded test from res/test3x4.ppm"),
            IPCTester.inputs("red-component test test-red extra"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q")));
  }
}
