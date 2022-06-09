package view;

import model.ExceptionMessage;

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
   *
   * @param out the specified output
   * @throws IllegalArgumentException if the appendable is null
   */
  public ImageProcessingTextView(Appendable out) throws IllegalArgumentException {
    if (out == null) {
      throw new IllegalArgumentException(
              String.format(ExceptionMessage.SPECIFIC_NULL_ARGUMENT.toString(), "Appendable"));
    }
    this.out = out;
  }

  @Override
  public void renderMessage(String msg) throws IOException {
    this.out.append(msg);
  }
}
