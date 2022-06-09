package controller;

import org.junit.Test;

import java.io.StringReader;

import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingTextView;
import view.ImageProcessingView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the {@code ImageProcessingControllerImpl} class.
 */
public class ImageProcessingControllerImplTest {

  /**
   * Creates a new input interaction.
   *
   * @param in the input text
   * @return the new interaction
   */
  static Interaction inputs(String in) {
    return (input, output) -> input.append(in);
  }

  /**
   * Creates a new print interaction.
   *
   * @param lines the printed lines
   * @return the new interaction
   */
  static Interaction prints(String... lines) {
    return (input, output) -> {
      for (String line : lines) {
        output.append(line).append('\n');
      }
    };
  }

  /**
   * Performs a test run and compares the generated expected output to the actual
   * output of the
   * tested controller.
   *
   * @param model        the image processing model
   * @param interactions the list of interactions with the controller
   * @return true when completed
   */
  boolean testRun(ImageProcessingModel model, Interaction... interactions) {
    StringBuilder fakeInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();
    for (Interaction interaction : interactions) {
      interaction.apply(fakeInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeInput.toString());
    StringBuilder actualOutput = new StringBuilder();
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            model, new ImageProcessingTextView(actualOutput), input);
    controller.start();
    assertEquals(expectedOutput.toString(), actualOutput.toString());
    return true;
  }

  @Test
  public void testConstructor() {
    Readable input = new StringReader("q\n");
    String expectedOutput = "*** Image Processing Program ***\nEnter a command to start.\n";
    Appendable output = new StringBuilder();

    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingTextView(output);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, input);
    controller.start();
    assertEquals(expectedOutput, output.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullModel() {
    ImageProcessingView view = new ImageProcessingTextView();
    ImageProcessingController controller = new ImageProcessingControllerImpl(null, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullView() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, null);
  }

  @Test
  public void testConstructorSpecifiedReadable() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingTextView();
    Readable in = new StringReader("");
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorSpecifiedReadableNullModel() {
    ImageProcessingView view = new ImageProcessingTextView();
    Readable in = new StringReader("");
    ImageProcessingController controller = new ImageProcessingControllerImpl(null, view, in);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorSpecifiedReadableNullView() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable in = new StringReader("");
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, null, in);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorSpecifiedReadableNullReadable() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingTextView();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, null);
  }
}