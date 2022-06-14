package controller.query;

import controller.loader.ImageLoaderFactory;
import model.ImageInterface;
import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * Represents the load query command.
 */
public class LoadQuery extends AbstractQueryCommand {

  public LoadQuery(ImageProcessingModel model, ImageProcessingView view) {
    super(model, view);
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    this.checkQueryLength(query, 3);
    String filePath = query[1];
    String imageName = query[2];
    ImageInterface loadedImage = ImageLoaderFactory.getImageLoader(filePath).load();
    this.model.storeImage(imageName, loadedImage);
    this.writeMessage("Successfully stored " + filePath + " as: " + imageName + ".\n");
  }
}
