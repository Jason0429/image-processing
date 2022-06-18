package view;

import controller.mocks.CorruptedAppendable;
import org.junit.Test;
import view.text.ImageProcessingTextView;
import view.text.ImageProcessingView;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the {@code ImageProcessingTextView} class.
 */
public class ImageProcessingTextViewTest {

  @Test
  public void testConstructor() {
    try {
      ImageProcessingView view = new ImageProcessingTextView();
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  @Test
  public void testAlternateConstructor() {
    try {
      ImageProcessingView view = new ImageProcessingTextView(new StringBuilder());
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAlternateConstructorNullAppendable() {
    ImageProcessingView view = new ImageProcessingTextView(null);
  }

  @Test
  public void testRenderMessage() {
    Appendable out = new StringBuilder();
    String expectedOutput = "Test Message\n";
    ImageProcessingView view = new ImageProcessingTextView(out);
    try {
      view.renderMessage("Test Message\n");
    } catch (IOException e) {
      fail();
    }
    assertEquals(expectedOutput, out.toString());
  }

  @Test(expected = IOException.class)
  public void testRenderMessageIOException() throws IOException {
    Appendable out = new CorruptedAppendable();
    ImageProcessingView view = new ImageProcessingTextView(out);
    view.renderMessage("Test Message\n");
  }
}
