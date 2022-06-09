package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingTextView;
import view.ImageProcessingView;

import static org.junit.Assert.assertEquals;

/**
 * Tests load command in controller.
 */
public class ControllerLoadTest {
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
    Readable readable = new StringReader(
            "load test/test-images/test3x4.ppm test" + System.lineSeparator() + "q");
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            this.model, this.view, readable);
    controller.start();
    assertEquals(appendable.toString(),
            "*** Image Processing Program ***\n" +
                    "Enter a command to start.\n" +
                    "Successfully loaded test from test/test-images/test3x4.ppm\n");
  }

  @Test
  public void testInvalid() {
    Readable readable = new StringReader(
            "load test/invalid.ppm invalid" + System.lineSeparator() + "q");
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            this.model, this.view, readable);
    controller.start();
    assertEquals(appendable.toString(),
            "*** Image Processing Program ***\n" +
                    "Enter a command to start.\n" +
                    "Invalid parameters specified, please try again.\n");
  }

  @Test
  public void testInvalidWithInts() {
    Readable readable = new StringReader(
            "load 1 23" + System.lineSeparator() + "q");
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            this.model, this.view, readable);
    controller.start();
    assertEquals(appendable.toString(),
            "*** Image Processing Program ***\n" +
                    "Enter a command to start.\n" +
                    "Invalid parameters specified, please try again.\n");
  }

  @Test
  public void testLessArgs() {
    Readable readable = new StringReader(
            "load test/test-images/test3x4.ppm" + System.lineSeparator() + "q");
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            this.model, this.view, readable);
    controller.start();
    assertEquals(appendable.toString(),
            "*** Image Processing Program ***\n" +
                    "Enter a command to start.\n" +
                    "Invalid parameters specified, please try again.\n");
  }

  @Test
  public void testMoreArgs() {
    Readable readable = new StringReader(
            "load test/test-images/test3x4.ppm" + System.lineSeparator() + "q");
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            this.model, this.view, readable);
    controller.start();
    assertEquals(appendable.toString(),
            "*** Image Processing Program ***\n" +
                    "Enter a command to start.\n" +
                    "Invalid parameters specified, please try again.\n");
  }
}
