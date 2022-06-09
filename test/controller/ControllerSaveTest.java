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
 * Tests for save command in controller.
 */
public class ControllerSaveTest {
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
            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
                    "vertical-flip test test-vertical" + System.lineSeparator() +
                    "save test/test-images/test-vertical.ppm test-vertical" +
                    System.lineSeparator() + "q");
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            this.model, this.view, readable);
    controller.start();
    assertEquals(appendable.toString(),
            "*** Image Processing Program ***\n" +
                    "Enter a command to start.\n" +
                    "Successfully loaded test from test/test-images/test3x4.ppm\n" +
                    "Successfully flipped image vertically and stored as: test-vertical\n" +
                    "Successfully saved test-vertical at " +
                    "test/test-images/test-vertical.ppm\n");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImageNotFound() {
    Readable readable = new StringReader(
            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
                    "vertical-flip test test-vertical" + System.lineSeparator() +
                    "save test/test-images/test-vertical.ppm does-not-exist" +
                    System.lineSeparator() + "q");
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            this.model, this.view, readable);
    controller.start();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalid() {
    Readable readable = new StringReader(
            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
                    "vertical-flip test test-vertical" + System.lineSeparator() +
                    "save 1 2" +
                    System.lineSeparator() + "q");
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            this.model, this.view, readable);
    controller.start();
  }

  @Test
  public void testLessArgs() {
    Readable readable = new StringReader(
            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
                    "vertical-flip test test-vertical" + System.lineSeparator() +
                    "save test/test-images/test-vertical.ppm" +
                    System.lineSeparator() + "q");
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            this.model, this.view, readable);
    controller.start();
    assertEquals(appendable.toString(),
            "*** Image Processing Program ***\n" +
                    "Enter a command to start.\n" +
                    "Successfully loaded test from test/test-images/test3x4.ppm\n" +
                    "Successfully flipped image vertically and stored as: test-vertical\n" +
                    "Invalid parameters specified, please try again.\n");
  }

  @Test
  public void testMoreArgs() {
    Readable readable = new StringReader(
            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
                    "vertical-flip test test-vertical" + System.lineSeparator() +
                    "save test/test-images/test-vertical.ppm test-vertical extra" +
                    System.lineSeparator() + "q");
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            this.model, this.view, readable);
    controller.start();
    assertEquals(appendable.toString(),
            "*** Image Processing Program ***\n" +
                    "Enter a command to start.\n" +
                    "Successfully loaded test from test/test-images/test3x4.ppm\n" +
                    "Successfully flipped image vertically and stored as: test-vertical\n" +
                    "Invalid parameters specified, please try again.\n");
  }
}
