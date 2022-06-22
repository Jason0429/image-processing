package controller.query;

import model.ImageProcessingModel;
import view.text.ImageProcessingTextView;

/**
 * Represents the list query command.
 */
public class ListQuery extends AbstractQueryCommand {

  public ListQuery(ImageProcessingModel model, ImageProcessingTextView view) {
    super(model, view);
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    String[] storedImageNames = this.model.getImageNames();

    if (storedImageNames.length == 0) {
      this.writeMessage("No images stored.\n");
      return;
    }

    this.writeMessage("List of images stored:\n");
    for (String name : storedImageNames) {
      this.writeMessage(name + "\n");
    }
  }
}
