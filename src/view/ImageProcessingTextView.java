package view;

import java.io.IOException;

/**
 * This is a text-based implementation of the image processing view.
 */
public class ImageProcessingTextView implements ImageProcessingView {
  private Appendable out;

  /**
   * Constructs a new image processing text view.
   */
  public ImageProcessingTextView() {
    this(System.out);
  }

  /**
   * Constructs a new image processing text view with a specified output.
   * @param out the specified output
   */
  public ImageProcessingTextView(Appendable out) {
    this.out = out;
  }

  @Override
  public void renderMessage(String msg) throws IOException {
    this.out.append(msg);
  }
}
