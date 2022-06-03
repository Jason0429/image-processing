import java.io.IOException;

/**
 * This class is a subclass of Appendable that always throws an IOException for testing.
 */
public class CorruptedAppendable implements Appendable {
  /**
   * Throws an IOException to test the view.
   * @return n/a
   * @throws IOException always
   */
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Test error handling");
  }

  /**
   * Throws an IOException to test the view.
   * @param csq   The character sequence from which a subsequence will be
   *              appended.  If {@code csq} is {@code null}, then characters
   *              will be appended as if {@code csq} contained the four
   *              characters {@code "null"}.
   * @param start The index of the first character in the subsequence
   * @param end   The index of the character following the last character in the
   *              subsequence
   * @return n/a
   * @throws IOException always
   */
  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Test error handling");
  }

  /**
   * Throws an IOException to test the view.
   *
   * @param c The character to append
   * @return n/a
   * @throws IOException always
   */
  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Test error handling");
  }
}
