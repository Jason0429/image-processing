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
 * Tests for intensity-component command for controller.
 */
public class ControllerIntensityComponentTest {
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
                    "intensity-component test test-intensity" + System.lineSeparator() + "q");
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            this.model, this.view, readable);
    controller.start();
    assertEquals(appendable.toString(),
            "*** Image Processing Program ***\n" +
                    "Enter a command to start.\n" +
                    "Successfully loaded test from test/test-images/test3x4.ppm\n" +
                    "Successfully applied intensity component and stored as: test-intensity\n");
  }

  @Test
  public void testImageDoesNotExist() {
    Readable readable = new StringReader(
            "load test/test-images/test3x4.ppm test" + System.lineSeparator() +
                    "intensity-component does-not-exist test-intensity" + System.lineSeparator() + "q");
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
                    "intensity-component 1 2" + System.lineSeparator() + "q");
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
                    "intensity-component test" + System.lineSeparator() + "q");
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
                    "intensity-component test test-intensity extra" + System.lineSeparator() + "q");
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
