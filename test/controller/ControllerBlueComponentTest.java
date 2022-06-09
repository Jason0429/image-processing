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
 * Tests blue-component command for controller.
 */
public class ControllerBlueComponentTest {
  private Appendable appendable;
  private ImageProcessingModel model;
  private ImageProcessingView view;

  @Before
  public void init() {
    this.appendable = new StringBuilder();
    this.model = new ImageProcessingModelImpl();
    this.view = new ImageProcessingTextView(this.appendable);
  }

  @Test
  public void testValid() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/test-images/test3x4.ppm test\n"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("blue-component test test-blue\n"),
            IPCTester.prints("Successfully applied blue component and stored as: test-blue"),
            IPCTester.inputs("q\n")));

//    Readable readable = new StringReader(
//            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
//                    "blue-component test test-blue" + System.lineSeparator() + "q");
//    ImageProcessingController controller = new ImageProcessingControllerImpl(
//            this.model, this.view, readable);
//    controller.start();
//    assertEquals(appendable.toString(),
//            "*** Image Processing Program ***\n" +
//                    "Enter a command to start.\n" +
//                    "Successfully loaded test from test/test-images/test3x4.ppm\n" +
//                    "Successfully applied blue component and stored as: test-blue\n");

  }

  @Test
  public void testImageDoesNotExist() {
    Readable readable = new StringReader(
            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
                    "blue-component does-not-exist test-blue" + System.lineSeparator() + "q");
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            this.model, this.view, readable);
    controller.start();
    assertEquals(appendable.toString(),
            "*** Image Processing Program ***\n" +
                    "Enter a command to start.\n" +
                    "Successfully loaded test from test/test-images/test3x4.ppm\n" +
                    "Invalid parameters specified, please try again.\n");
  }

  @Test
  public void testInvalid() {
    Readable readable = new StringReader(
            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
                    "blue-component 1 2" + System.lineSeparator() + "q");
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            this.model, this.view, readable);
    controller.start();
    assertEquals(appendable.toString(),
            "*** Image Processing Program ***\n" +
                    "Enter a command to start.\n" +
                    "Successfully loaded test from test/test-images/test3x4.ppm\n" +
                    "Invalid parameters specified, please try again.\n");
  }

  @Test
  public void testLessArgs() {
    Readable readable = new StringReader(
            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
                    "blue-component test" + System.lineSeparator() + "q");
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            this.model, this.view, readable);
    controller.start();
    assertEquals(appendable.toString(),
            "*** Image Processing Program ***\n" +
                    "Enter a command to start.\n" +
                    "Successfully loaded test from test/test-images/test3x4.ppm\n" +
                    "Invalid parameters specified, please try again.\n");
  }

  @Test
  public void testMoreArgs() {
    Readable readable = new StringReader(
            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
                    "blue-component test test-green extra" + System.lineSeparator() + "q");
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            this.model, this.view, readable);
    controller.start();
    assertEquals(appendable.toString(),
            "*** Image Processing Program ***\n" +
                    "Enter a command to start.\n" +
                    "Successfully loaded test from test/test-images/test3x4.ppm\n" +
                    "Invalid parameters specified, please try again.\n");
  }
}
