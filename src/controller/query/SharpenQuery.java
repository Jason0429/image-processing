package controller.query;

import model.ImageInterface;
import model.ImageProcessingModel;
import model.commands.SharpenCommand;
import view.text.ImageProcessingTextView;

/**
 * Represents the sharpen query command.
 */
public class SharpenQuery extends AbstractQueryCommand {

  public SharpenQuery(ImageProcessingModel model, ImageProcessingTextView view) {
    super(model, view);
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    this.checkQueryLength(query, 3);
    String unprocessedImageName = query[1];
    String processedImageName = query[2];
    ImageInterface unprocessedImage = this.model.getImage(unprocessedImageName);
    ImageInterface processedImage = new SharpenCommand().process(unprocessedImage);
    this.model.storeImage(processedImageName, processedImage);
    this.writeMessage(
            "Successfully sharpened image and stored as: " + processedImageName + ".\n");
  }
}
