package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingTextView;
import view.ImageProcessingView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for green-component command in controller.
 */
public class ControllerGreenComponentTest {
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
            IPCTester.inputs("load test/test-images/test3x4.ppm test\n"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("green-component test test-green\n"),
            IPCTester.prints("Successfully applied green component and stored as: test-green"),
            IPCTester.inputs("q\n")));
  }

  @Test
  public void testImageDoesNotExist() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/test-images/test3x4.ppm test\n"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("green-component does-not-exist test-green\n"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q\n")));
  }

  @Test
  public void testInvalid() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/test-images/test3x4.ppm test\n"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("green-component 1 2\n"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q\n")));
  }

  @Test
  public void testLessArgs() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/test-images/test3x4.ppm test\n"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("green-component test\n"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q\n")));
  }

  @Test
  public void testMoreArgs() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/test-images/test3x4.ppm test\n"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("green-component test test-green extra\n"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q\n")));
  }
}
