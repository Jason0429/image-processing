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
 * Tests brighten command for controller.
 */
public class ControllerBrightenTest {
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
  public void testValidBrighten() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/test-images/test3x4.ppm test\n"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("brighten 10 test test-brighten-10\n"),
            IPCTester.prints("Successfully brightened image and stored as: test-brighten-10"),
            IPCTester.inputs("q\n")));

//    Readable readable = new StringReader(
//            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
//                    "brighten 10 test test-brighten-10" + System.lineSeparator() + "q");
//    ImageProcessingController controller = new ImageProcessingControllerImpl(
//            this.model, this.view, readable);
//    controller.start();
//    assertEquals(appendable.toString(),
//            "*** Image Processing Program ***\n" +
//                    "Enter a command to start.\n" +
//                    "Successfully loaded test from test/test-images/test3x4.ppm\n" +
//                    "Successfully brightened image and stored as: test-brighten-10\n");
  }

  @Test
  public void testValidDarken() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/test-images/test3x4.ppm test\n"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("brighten -10 test test-brighten-10\n"),
            IPCTester.prints("Successfully brightened image and stored as: test-darken-10"),
            IPCTester.inputs("q\n")));

//    Readable readable = new StringReader(
//            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
//                    "brighten -10 test test-darken-10" + System.lineSeparator() + "q");
//    ImageProcessingController controller = new ImageProcessingControllerImpl(
//            this.model, this.view, readable);
//    controller.start();
//    assertEquals(appendable.toString(),
//            "*** Image Processing Program ***\n" +
//                    "Enter a command to start.\n" +
//                    "Successfully loaded test from test/test-images/test3x4.ppm\n" +
//                    "Successfully brightened image and stored as: test-darken-10\n");
  }

  @Test
  public void testImageDoesNotExist() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/test-images/test3x4.ppm test\n"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("brighten 10 does-not-exist test-brighten-10\n"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q\n")));

//    Readable readable = new StringReader(
//            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
//                    "brighten 10 does-not-exist test-brighten-10" + System.lineSeparator() + "q");
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
  public void testInvalidArgs() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load test/test-images/test3x4.ppm test\n"),
            IPCTester.prints("Successfully loaded test from test/test-images/test3x4.ppm"),
            IPCTester.inputs("brighten 1 -h la\n"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q\n")));

//    Readable readable = new StringReader(
//            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
//                    "brighten 1 -h la" + System.lineSeparator() + "q");
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
            IPCTester.inputs("brighten 1\n"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q\n")));

//    Readable readable = new StringReader(
//            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
//                    "brighten 1" + System.lineSeparator() + "q");
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
            IPCTester.inputs("brighten 10 test test-brighten-10 extra\n"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q\n")));

//    Readable readable = new StringReader(
//            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
//                    "brighten 10 test test-brighten-10 extra" + System.lineSeparator() + "q");
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
