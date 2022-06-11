package controller.query;

import model.ImageInterface;
import model.ImageProcessingModel;
import model.commands.BlueComponentGreyscaleCommand;
import view.ImageProcessingView;

/**
 * Represents the blue component query command.
 */
public class BlueComponentQuery extends AbstractQueryCommand {

  public BlueComponentQuery(ImageProcessingModel model, ImageProcessingView view) {
    super(model, view);
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    this.checkQueryLength(query, 3);
    String unprocessedImageName = query[1];
    String processedImageName = query[2];
    ImageInterface unprocessedImage = this.model.getImage(unprocessedImageName);
    ImageInterface processedImage = new BlueComponentGreyscaleCommand().process(unprocessedImage);
    this.model.storeImage(processedImageName, processedImage);
    this.writeMessage("Successfully applied blue component and stored as: " + query[2] + "\n");
  }
}