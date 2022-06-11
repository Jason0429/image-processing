package controller.query;

import model.Image;
import model.ImageProcessingModel;
import model.commands.BlueComponentGreyscaleCommand;
import model.commands.ImageProcessingCommand;
import view.ImageProcessingView;

public class BlueComponentQuery extends AbstractQueryCommand {

  public BlueComponentQuery(ImageProcessingModel model, ImageProcessingView view) {
    super(model, view);
  }

  @Override
  public void executeCommand(String[] query) throws IllegalArgumentException {
    super.checkQueryLength(query, 3);
    ImageProcessingCommand cmd = new BlueComponentGreyscaleCommand();
    Image unprocessedImage = this.model.getImage(query[1]);
    String processedImageName = query[2];
    Image processedImage = cmd.process(unprocessedImage);
    this.model.storeImage(processedImageName, processedImage);
  }
}