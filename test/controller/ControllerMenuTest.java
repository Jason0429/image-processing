package controller;

import org.junit.Before;
import org.junit.Test;

import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;

import static org.junit.Assert.assertTrue;

/**
 * Tests menu command in controller.
 */
public class ControllerMenuTest {
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
            IPCTester.inputs("menu"),
            IPCTester.prints("Options:"),
            IPCTester.prints("menu (loads this menu)"),
            IPCTester.prints("list (lists all loaded images)"),
            IPCTester.prints("load [image-path] [image-name]"),
            IPCTester.prints("save [image-path] [image-name]"),
            IPCTester.prints("red-component [image-name] [dest-image-name]"),
            IPCTester.prints("green-component [image-name] [dest-image-name]"),
            IPCTester.prints("blue-component [image-name] [dest-image-name]"),
            IPCTester.prints("value-component [image-name] [dest-image-name]"),
            IPCTester.prints("luma-component [image-name] [dest-image-name]"),
            IPCTester.prints("intensity-component [image-name] [dest-image-name]"),
            IPCTester.prints("horizontal-flip [image-name] [dest-image-name]"),
            IPCTester.prints("vertical-flip [image-name] [dest-image-name]"),
            IPCTester.prints("brighten [image-name] [dest-image-name] [increment]"),
            IPCTester.prints("gaussian-blur [image-name] [dest-image-name]"),
            IPCTester.prints("sharpen [image-name] [dest-image-name]"),
            IPCTester.prints("* [source-image-name] [mask-image-name] [dest-image-name] "
                    + "[command-args, if any] where * is any "
                    + "image processing command except flipping."),
            IPCTester.prints("quit/q (quit the program)"),
            IPCTester.inputs("q"),
            IPCTester.prints("Quitting Image Processing...")));
  }

  @Test
  public void testAlsoValid() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("menu 1 2 3"),
            IPCTester.prints("Options:"),
            IPCTester.prints("menu (loads this menu)"),
            IPCTester.prints("list (lists all loaded images)"),
            IPCTester.prints("load [image-path] [image-name]"),
            IPCTester.prints("save [image-path] [image-name]"),
            IPCTester.prints("red-component [image-name] [dest-image-name]"),
            IPCTester.prints("green-component [image-name] [dest-image-name]"),
            IPCTester.prints("blue-component [image-name] [dest-image-name]"),
            IPCTester.prints("value-component [image-name] [dest-image-name]"),
            IPCTester.prints("luma-component [image-name] [dest-image-name]"),
            IPCTester.prints("intensity-component [image-name] [dest-image-name]"),
            IPCTester.prints("horizontal-flip [image-name] [dest-image-name]"),
            IPCTester.prints("vertical-flip [image-name] [dest-image-name]"),
            IPCTester.prints("brighten [image-name] [dest-image-name] [increment]"),
            IPCTester.prints("gaussian-blur [image-name] [dest-image-name]"),
            IPCTester.prints("sharpen [image-name] [dest-image-name]"),
            IPCTester.prints("* [source-image-name] [mask-image-name] [dest-image-name] "
                    + "[command-args, if any] where * is any "
                    + "image processing command except flipping."),
            IPCTester.prints("quit/q (quit the program)"),
            IPCTester.inputs("q"),
            IPCTester.prints("Quitting Image Processing...")));
  }
}
