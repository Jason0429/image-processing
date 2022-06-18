package controller.query;

import model.ImageInterface;
import model.ImageProcessingModel;
import model.commands.LumaProcessingCommand;
import view.text.ImageProcessingView;

/**
 * Represents the luma component query command.
 */
public class LumaQuery extends AbstractQueryCommand {

  public LumaQuery(ImageProcessingModel model, ImageProcessingView view) {
    super(model, view);
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    this.checkQueryLength(query, 3);
    String unprocessedImageName = query[1];
    String processedImageName = query[2];
    ImageInterface unprocessedImage = this.model.getImage(unprocessedImageName);
    ImageInterface processedImage = new LumaProcessingCommand().process(unprocessedImage);
    this.model.storeImage(processedImageName, processedImage);
    this.writeMessage(
            "Successfully applied luma component and stored as: " + processedImageName + ".\n");
  }
}