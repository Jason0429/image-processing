package controller.query;

import model.ImageInterface;
import model.ImageProcessingModel;
import model.commands.SepiaProcessingCommand;
import view.ImageProcessingView;

/**
 * Represents the sepia query command.
 */
public class SepiaQuery extends AbstractQueryCommand {

  public SepiaQuery(ImageProcessingModel model, ImageProcessingView view) {
    super(model, view);
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    this.checkQueryLength(query, 3);
    String unprocessedImageName = query[1];
    String processedImageName = query[2];
    ImageInterface unprocessedImage = this.model.getImage(unprocessedImageName);
    ImageInterface processedImage = new SepiaProcessingCommand().process(unprocessedImage);
    this.model.storeImage(processedImageName, processedImage);
    this.writeMessage(
            "Successfully applied sepia and stored as: " + processedImageName + ".\n");
  }
}
