package controller.query;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * Represents the list query command.
 */
public class ListQuery extends AbstractQueryCommand {

  public ListQuery(ImageProcessingModel model, ImageProcessingView view) {
    super(model, view);
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    String[] storedImageNames = this.model.getImageNames();

    if (storedImageNames.length == 0) {
      this.writeMessage("No images stored.");
      return;
    }

    this.writeMessage("List of images stored:\n");
    if (storedImageNames.length == 0) {
      this.writeMessage("There are no images stored at the moment.\n");
    } else {
      for (String name : storedImageNames) {
        this.writeMessage(name + "\n");
      }
    }
  }
}
