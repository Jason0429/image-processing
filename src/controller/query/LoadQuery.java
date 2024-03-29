package controller.query;

import controller.loader.ImageLoader;
import model.ImageInterface;
import model.ImageProcessingModel;
import view.text.ImageProcessingTextView;

/**
 * Represents the load query command.
 */
public class LoadQuery extends AbstractQueryCommand {

  public LoadQuery(ImageProcessingModel model, ImageProcessingTextView view) {
    super(model, view);
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    this.checkQueryLength(query, this.getProperQueryLength());
    String filePath = query[1];
    String imageName = query[2];
    ImageInterface loadedImage = ImageLoader.load(filePath);
    this.model.storeImage(imageName, loadedImage);
    this.writeMessage("Successfully stored " + filePath + " as: " + imageName + ".\n");
  }
}
