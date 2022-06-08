package view;

import org.junit.Test;

import java.io.IOException;

import controller.CorruptedAppendable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the {@code ImageProcessingTextView} class.
 */
public class ImageProcessingTextViewTest {

  @Test
  public void testConstructor() {
    ImageProcessingView view = new ImageProcessingTextView();
  }

  @Test
  public void testAlternateConstructor() {
    ImageProcessingView view = new ImageProcessingTextView(new StringBuilder());
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
