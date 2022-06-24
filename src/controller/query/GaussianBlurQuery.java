package controller.query;

import model.ImageInterface;
import model.ImageProcessingModel;
import model.commands.GaussianBlurCommand;
import view.text.ImageProcessingTextView;

/**
 * Represents the gaussian blur query command.
 */
public class GaussianBlurQuery extends AbstractQueryCommand {

  public GaussianBlurQuery(ImageProcessingModel model, ImageProcessingTextView view) {
    super(model, view);
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    this.checkQueryLength(query, this.getProperQueryLength());
    String unprocessedImageName = query[1];
    String processedImageName = query[2];
    ImageInterface unprocessedImage = this.model.getImage(unprocessedImageName);
    ImageInterface processedImage = new GaussianBlurCommand().process(unprocessedImage);
    this.model.storeImage(processedImageName, processedImage);
    this.writeMessage("Successfully blurred image and stored as: " + processedImageName + ".\n");
  }
}
