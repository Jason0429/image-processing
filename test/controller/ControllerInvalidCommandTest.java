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
 * Tests invalid commands for controller.
 */
public class ControllerInvalidCommandTest {
  private ImageProcessingModel model;

  @Before
  public void init() {
    this.model = new ImageProcessingModelImpl();
  }

  @Test
  public void testInvalidCommandEntered() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("invalid-command"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q")));
  }
}
