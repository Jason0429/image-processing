package controller.query;

import model.ImageInterface;
import model.ImageProcessingModel;
import model.commands.FlipHorizontalCommand;
import view.text.ImageProcessingTextView;

/**
 * Represents the flip horizontal query command.
 */
public class FlipHorizontalQuery extends AbstractQueryCommand {

  public FlipHorizontalQuery(ImageProcessingModel model, ImageProcessingTextView view) {
    super(model, view);
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    this.checkQueryLength(query, this.getProperQueryLength());
    String unprocessedImageName = query[1];
    String processedImageName = query[2];
    ImageInterface unprocessedImage = this.model.getImage(unprocessedImageName);
    ImageInterface processedImage = new FlipHorizontalCommand().process(unprocessedImage);
    this.model.storeImage(processedImageName, processedImage);
    this.writeMessage(
            "Successfully flipped image horizontally and stored as: " + processedImageName + ".\n");
  }
}