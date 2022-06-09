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
 * Tests horizontal-component for controller.
 */
public class ControllerHorizontalFlipTest {
  //  private Appendable appendable;
  private ImageProcessingModel model;
//  private ImageProcessingView view;

  @Before
  public void init() {
//    this.appendable = new StringBuilder();
    this.model = new ImageProcessingModelImpl();
//    this.view = new ImageProcessingTextView(this.appendable);
  }

  @Test
  public void testValid() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/test-images/test3x4.ppm test\n"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("horizontal-flip test test-horizontal\n"),
            IPCTester.prints(
                    "Successfully flipped image horizontally and stored as: test-horizontal"),
            IPCTester.inputs("q\n")));

//    Readable readable = new StringReader(
//            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
//                    "horizontal-flip test test-horizontal" + System.lineSeparator() + "q");
//    ImageProcessingController controller = new ImageProcessingControllerImpl(
//            this.model, this.view, readable);
//    controller.start();
//    assertEquals(appendable.toString(),
//            "*** Image Processing Program ***\n" +
//                    "Enter a command to start.\n" +
//                    "Successfully loaded test from test/test-images/test3x4.ppm\n" +
//                    "Successfully flipped image horizontally and stored as: test-horizontal\n");
  }

  @Test
  public void testImageDoesNotExist() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/test-images/test3x4.ppm test\n"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("horizontal-flip does-not-exist test-horizontal\n"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q\n")));

//    Readable readable = new StringReader(
//            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
//                    "horizontal-flip does-not-exist test-horizontal" + System.lineSeparator() + "q");
//    ImageProcessingController controller = new ImageProcessingControllerImpl(
//            this.model, this.view, readable);
//    controller.start();
//    assertEquals(appendable.toString(),
//            "*** Image Processing Program ***\n" +
//                    "Enter a command to start.\n" +
//                    "Successfully loaded test from test/test-images/test3x4.ppm\n" +
//                    "Invalid parameters specified, please try again.\n");
  }

  @Test
  public void testInvalid() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/test-images/test3x4.ppm test\n"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("horizontal-flip 1 2\n"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q\n")));

//    Readable readable = new StringReader(
//            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
//                    "horizontal-flip 1 2" + System.lineSeparator() + "q");
//    ImageProcessingController controller = new ImageProcessingControllerImpl(
//            this.model, this.view, readable);
//    controller.start();
//    assertEquals(appendable.toString(),
//            "*** Image Processing Program ***\n" +
//                    "Enter a command to start.\n" +
//                    "Successfully loaded test from test/test-images/test3x4.ppm\n" +
//                    "Invalid parameters specified, please try again.\n");
  }

  @Test
  public void testLessArgs() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/test-images/test3x4.ppm test\n"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("horizontal-flip test\n"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q\n")));

//    Readable readable = new StringReader(
//            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
//                    "horizontal-flip test" + System.lineSeparator() + "q");
//    ImageProcessingController controller = new ImageProcessingControllerImpl(
//            this.model, this.view, readable);
//    controller.start();
//    assertEquals(appendable.toString(),
//            "*** Image Processing Program ***\n" +
//                    "Enter a command to start.\n" +
//                    "Successfully loaded test from test/test-images/test3x4.ppm\n" +
//                    "Invalid parameters specified, please try again.\n");
  }

  @Test
  public void testMoreArgs() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/test-images/test3x4.ppm test\n"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("horizontal-flip test test-horizontal extra\n"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q\n")));

//    Readable readable = new StringReader(
//            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
//                    "horizontal-flip test test-horizontal extra" + System.lineSeparator() + "q");
//    ImageProcessingController controller = new ImageProcessingControllerImpl(
//            this.model, this.view, readable);
//    controller.start();
//    assertEquals(appendable.toString(),
//            "*** Image Processing Program ***\n" +
//                    "Enter a command to start.\n" +
//                    "Successfully loaded test from test/test-images/test3x4.ppm\n" +
//                    "Invalid parameters specified, please try again.\n");
  }
}
