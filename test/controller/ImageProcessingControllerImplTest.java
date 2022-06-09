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