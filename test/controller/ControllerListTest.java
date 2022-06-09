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
            IPCTester.inputs("load test/test-images/test3x4.ppm test"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("list"),
            IPCTester.prints("test"),
            IPCTester.inputs("q")));
  }

  @Test
  public void testValidMultipleImages() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/test-images/test3x4.ppm test"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("load test/test-images/test3x4.ppm test1"),
            IPCTester.prints("Successfully loaded test1 from test/test-images/test3x4.ppm"),
            IPCTester.inputs("list"),
            IPCTester.prints("test\ntest1"),
            IPCTester.inputs("q")));
  }

  @Test
  public void testEmptyList() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("list"),
            IPCTester.prints("There are no images stored at the moment."),
            IPCTester.inputs("q")));
  }
}
