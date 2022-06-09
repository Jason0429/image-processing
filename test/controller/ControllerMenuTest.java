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
 * Tests menu command in controller.
 */
public class ControllerMenuTest {
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
            "menu" + System.lineSeparator() + "q");
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            this.model, this.view, readable);
    controller.start();
    assertEquals(appendable.toString(),
            "*** Image Processing Program ***\n" +
                    "Enter a command to start.\n" +
                    "Options:\n" +
                    "load [image-path] [image-name]\n" +
                    "save [image-path] [image-name]\n" +
                    "red-component [image-name] [dest-image-name]\n" +
                    "green-component [image-name] [dest-image-name]\n" +
                    "blue-component [image-name] [dest-image-name]\n" +
                    "value-component [image-name] [dest-image-name]\n" +
                    "luma-component [image-name] [dest-image-name]\n" +
                    "intensity-component [image-name] [dest-image-name]\n" +
                    "horizontal-flip [image-name] [dest-image-name]\n" +
                    "vertical-flip [image-name] [dest-image-name]\n" +
                    "brighten [increment] [image-name] [dest-image-name]\n");
  }

  @Test
  public void testAlsoValid() {
    Readable readable = new StringReader(
            "menu 2 3 4" + System.lineSeparator() + "q");
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            this.model, this.view, readable);
    controller.start();
    assertEquals(appendable.toString(),
            "*** Image Processing Program ***\n" +
                    "Enter a command to start.\n" +
                    "Options:\n" +
                    "load [image-path] [image-name]\n" +
                    "save [image-path] [image-name]\n" +
                    "red-component [image-name] [dest-image-name]\n" +
                    "green-component [image-name] [dest-image-name]\n" +
                    "blue-component [image-name] [dest-image-name]\n" +
                    "value-component [image-name] [dest-image-name]\n" +
                    "luma-component [image-name] [dest-image-name]\n" +
                    "intensity-component [image-name] [dest-image-name]\n" +
                    "horizontal-flip [image-name] [dest-image-name]\n" +
                    "vertical-flip [image-name] [dest-image-name]\n" +
                    "brighten [increment] [image-name] [dest-image-name]\n");
  }
}
