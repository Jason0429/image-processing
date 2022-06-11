package controller.query;

import model.ImageInterface;
import model.ImageProcessingModel;
import model.commands.LumaComponentGreyscaleCommand;
import view.ImageProcessingView;

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
    ImageInterface processedImage = new LumaComponentGreyscaleCommand().process(unprocessedImage);
    this.model.storeImage(processedImageName, processedImage);
    this.writeMessage(
            "Successfully applied luma component and stored as: " + processedImageName + ".\n");
  }
}