package controller;

import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import org.junit.Before;
import org.junit.Test;
import view.ImageProcessingTextView;
import view.ImageProcessingView;

import java.io.StringReader;

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
    assertEquals("*** Image Processing Program ***\n" +
            "Enter a command to start.\n" +
            "Options:\n" +
            "menu (loads this menu)\n" +
            "list (lists all loaded images)\n" +
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
            "brighten [increment] [image-name] [dest-image-name]\n" +
            "quit/q (quit the program)\n", appendable.toString());
  }

  @Test
  public void testAlsoValid() {
    Readable readable = new StringReader(
            "menu 2 3 4" + System.lineSeparator() + "q");
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            this.model, this.view, readable);
    controller.start();
    assertEquals("*** Image Processing Program ***\n" +
            "Enter a command to start.\n" +
            "Options:\n" +
            "menu (loads this menu)\n" +
            "list (lists all loaded images)\n" +
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
            "brighten [increment] [image-name] [dest-image-name]\n" +
            "quit/q (quit the program)\n", appendable.toString());
  }
}
