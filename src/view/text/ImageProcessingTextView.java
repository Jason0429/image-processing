package view.text;

import java.io.IOException;

/**
 * This interface contains the methods for a view for an image processing program.
 */
public interface ImageProcessingTextView {

  /**
   * Renders a specified message to the output.
   *
   * @param msg the message to be rendered
   */
  void renderMessage(String msg) throws IOException;
}
