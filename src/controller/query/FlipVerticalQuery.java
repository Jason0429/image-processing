package controller.query;

import model.ImageInterface;
import model.ImageProcessingModel;
import model.commands.FlipVerticalCommand;
import view.text.ImageProcessingTextView;

/**
 * Represents the flip vertical query command.
 */
public class FlipVerticalQuery extends AbstractQueryCommand {

  public FlipVerticalQuery(ImageProcessingModel model, ImageProcessingTextView view) {
    super(model, view);
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    this.checkQueryLength(query, this.getProperQueryLength());
    String unprocessedImageName = query[1];
    String processedImageName = query[2];
    ImageInterface unprocessedImage = this.model.getImage(unprocessedImageName);
    ImageInterface processedImage = new FlipVerticalCommand().process(unprocessedImage);
    this.model.storeImage(processedImageName, processedImage);
    this.writeMessage(
            "Successfully flipped image vertically and stored as: " + processedImageName + ".\n");
  }
}
