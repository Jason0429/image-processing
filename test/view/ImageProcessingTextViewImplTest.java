package view;

import controller.mocks.CorruptedAppendable;
import org.junit.Test;
import view.text.ImageProcessingTextTextViewImpl;
import view.text.ImageProcessingTextView;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the {@code ImageProcessingTextView} class.
 */
public class ImageProcessingTextViewImplTest {

  @Test
  public void testConstructor() {
    try {
      ImageProcessingTextView view = new ImageProcessingTextTextViewImpl();
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  @Test
  public void testAlternateConstructor() {
    try {
      ImageProcessingTextView view = new ImageProcessingTextTextViewImpl(new StringBuilder());
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAlternateConstructorNullAppendable() {
    ImageProcessingTextView view = new ImageProcessingTextTextViewImpl(null);
  }

  @Test
  public void testRenderMessage() {
    Appendable out = new StringBuilder();
    String expectedOutput = "Test Message\n";
    ImageProcessingTextView view = new ImageProcessingTextTextViewImpl(out);
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
    ImageProcessingTextView view = new ImageProcessingTextTextViewImpl(out);
    view.renderMessage("Test Message\n");
  }
}
