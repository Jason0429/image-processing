package controller.query;

import model.ImageInterface;
import model.ImageProcessingModel;
import model.commands.GreenComponentGreyscaleCommand;
import view.text.ImageProcessingTextView;

/**
 * Represents the green component query command.
 */
public class GreenComponentQuery extends AbstractQueryCommand {

  public GreenComponentQuery(ImageProcessingModel model, ImageProcessingTextView view) {
    super(model, view);
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    this.checkQueryLength(query, this.getProperQueryLength());
    String unprocessedImageName = query[1];
    String processedImageName = query[2];
    ImageInterface unprocessedImage = this.model.getImage(unprocessedImageName);
    ImageInterface processedImage = new GreenComponentGreyscaleCommand().process(unprocessedImage);
    this.model.storeImage(processedImageName, processedImage);
    this.writeMessage(
            "Successfully applied green component and stored as: " + processedImageName + ".\n");
  }
}