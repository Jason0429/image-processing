package controller.query;

import model.ImageInterface;
import model.ImageProcessingModel;
import model.commands.RedComponentGreyscaleCommand;
import view.ImageProcessingView;

/**
 * Represents the red component query command.
 */
public class RedComponentQuery extends AbstractQueryCommand {

  public RedComponentQuery(ImageProcessingModel model, ImageProcessingView view) {
    super(model, view);
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    this.checkQueryLength(query, 3);
    String unprocessedImageName = query[1];
    String processedImageName = query[2];
    ImageInterface unprocessedImage = this.model.getImage(unprocessedImageName);
    ImageInterface processedImage = new RedComponentGreyscaleCommand().process(unprocessedImage);
    this.model.storeImage(processedImageName, processedImage);
    this.writeMessage(
            "Successfully applied red component and stored as: " + processedImageName + ".\n");
  }
}