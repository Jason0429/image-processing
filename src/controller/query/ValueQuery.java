package controller.query;

import model.ImageInterface;
import model.ImageProcessingModel;
import model.commands.ValueComponentGreyscaleCommand;
import view.text.ImageProcessingTextView;

/**
 * Represents the value component query command.
 */
public class ValueQuery extends AbstractQueryCommand {

  public ValueQuery(ImageProcessingModel model, ImageProcessingTextView view) {
    super(model, view);
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    this.checkQueryLength(query, this.getProperQueryLength());
    String unprocessedImageName = query[1];
    String processedImageName = query[2];
    ImageInterface unprocessedImage = this.model.getImage(unprocessedImageName);
    ImageInterface processedImage = new ValueComponentGreyscaleCommand().process(unprocessedImage);
    this.model.storeImage(processedImageName, processedImage);
    this.writeMessage(
            "Successfully applied value component and stored as: " + processedImageName + ".\n");
  }
}

