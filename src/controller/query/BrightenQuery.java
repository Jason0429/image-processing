package controller.query;

import model.ImageInterface;
import model.ImageProcessingModel;
import model.commands.BrightenCommand;
import view.ImageProcessingView;

/**
 * Represents the brighten query command.
 */
public class BrightenQuery extends AbstractQueryCommand {

  public BrightenQuery(ImageProcessingModel model, ImageProcessingView view) {
    super(model, view);
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    this.checkQueryLength(query, 4);
    String unprocessedImageName = query[1];
    String processedImageName = query[2];
    int brightenLevel = Integer.parseInt(query[3]);
    ImageInterface unprocessedImage = this.model.getImage(unprocessedImageName);
    ImageInterface processedImage = new BrightenCommand(brightenLevel).process(unprocessedImage);
    this.model.storeImage(processedImageName, processedImage);
    this.writeMessage("Successfully brightened image and stored as: " + processedImageName + "\n");
  }
}
