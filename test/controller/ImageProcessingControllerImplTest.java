package controller;

import org.junit.Test;

import java.io.StringReader;

import controller.mocks.MockImageProcessingModel;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingTextView;
import view.ImageProcessingView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the {@code ImageProcessingControllerImpl} class.
 */
public class ImageProcessingControllerImplTest {

  @Test
  public void testConstructor() {
    Readable input = new StringReader("q\n");
    String expectedOutput = "*** Image Processing Program ***\n" +
            "Enter a command to start.\n" +
            "Quitting Image Processing...\n";
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
    String expectedOutput = "*** Image Processing Program ***\n" +
            "Enter a command to start.\n" +
            "Quitting Image Processing...\n";
    ImageProcessingModel model = new ImageProcessingModelImpl();
    Appendable out = new StringBuilder();
    ImageProcessingView view = new ImageProcessingTextView(out);
    Readable in = new StringReader("q\n");
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
    controller.start();
    assertEquals(expectedOutput, out.toString());
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

  @Test
  public void testControllerPassingInputsToModel() {
    Appendable log = new StringBuilder();
    Readable in = new StringReader("load res/test3x4.ppm test\n"
            + "brighten test test-brighten 10\n"
            + "list\n"
            + "q\n");
    ImageProcessingModel model = new MockImageProcessingModel(log);
    ImageProcessingView view = new ImageProcessingTextView();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, in);
    controller.start();
    assertEquals("Store Image: test\nGet Image: test\n" +
            "Get Image Names\n", log.toString());
  }
}