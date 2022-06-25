package controller;

import org.junit.Test;

import java.io.StringReader;

import controller.mocks.MockImageProcessingModel;
import controller.text.ImageProcessingTextControllerImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.text.ImageProcessingTextViewImpl;
import view.text.ImageProcessingTextView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the {@code ImageProcessingControllerImpl} class.
 */
public class ImageProcessingTextControllerImplTest {

  @Test
  public void testConstructor() {
    Readable input = new StringReader("q\n");
    String expectedOutput = "*** Image Processing Program ***\n" +
            "Enter a command to start.\n" +
            "Quitting Image Processing...\n";
    Appendable output = new StringBuilder();

    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(output);
    ImageProcessingController controller = new ImageProcessingTextControllerImpl(model, view, input);
    controller.start();
    assertEquals(expectedOutput, output.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullModel() {
    ImageProcessingTextView view = new ImageProcessingTextViewImpl();
    ImageProcessingController controller = new ImageProcessingTextControllerImpl(null, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullView() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingTextControllerImpl(model, null);
  }

  @Test
  public void testConstructorSpecifiedReadable() {
    String expectedOutput = "*** Image Processing Program ***\n" +
            "Enter a command to start.\n" +
            "Quitting Image Processing...\n";
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Appendable out = new StringBuilder();
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(out);
    Readable in = new StringReader("q\n");
    ImageProcessingController controller = new ImageProcessingTextControllerImpl(model, view, in);
    controller.start();
    assertEquals(expectedOutput, out.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorSpecifiedReadableNullModel() {
    ImageProcessingTextView view = new ImageProcessingTextViewImpl();
    Readable in = new StringReader("");
    ImageProcessingController controller = new ImageProcessingTextControllerImpl(null, view, in);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorSpecifiedReadableNullView() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Readable in = new StringReader("");
    ImageProcessingController controller = new ImageProcessingTextControllerImpl(model, null, in);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorSpecifiedReadableNullReadable() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingTextView view = new ImageProcessingTextViewImpl();
    ImageProcessingController controller = new ImageProcessingTextControllerImpl(model, view, null);
  }

  @Test
  public void testControllerPassingInputsToModel() {
    Appendable log = new StringBuilder();
    Readable in = new StringReader("load res/test3x4.ppm test\n"
            + "brighten test test-brighten 10\n"
            + "list\n"
            + "q\n");
    ImageProcessingModel model = new MockImageProcessingModel(log);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl();
    ImageProcessingController controller = new ImageProcessingTextControllerImpl(model, view, in);
    controller.start();
    assertEquals("Store Image: test\nGet Image: test\n" +
            "Get Image Names\n", log.toString());
  }
}