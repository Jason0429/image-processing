package view;

import java.io.IOException;

/**
 * This interface contains the methods for a view for an image processing program.
 */
public interface ImageProcessingView {

  /**
   * Renders a specified message to the output.
   * @param msg the message to be rendered
   */
  public void renderMessage(String msg) throws IOException;
}
