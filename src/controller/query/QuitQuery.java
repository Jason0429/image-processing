package controller.query;

import model.ImageProcessingModel;
import view.text.ImageProcessingTextView;

/**
 * Represents the quit query command.
 */
public class QuitQuery extends AbstractQueryCommand {
  private final Runnable runnable;

  public QuitQuery(ImageProcessingModel model, ImageProcessingTextView view, Runnable runnable) {
    super(model, view);
    this.runnable = runnable;
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    this.runnable.run();
    this.writeMessage("Quitting Image Processing...\n");
  }
}
