package controller.query;

import model.ImageInterface;
import model.ImageProcessingModel;
import model.commands.IntensityComponentGreyscaleCommand;
import view.text.ImageProcessingView;

/**
 * Represents the intensity query command.
 */
public class IntensityQuery extends AbstractQueryCommand {

  public IntensityQuery(ImageProcessingModel model, ImageProcessingView view) {
    super(model, view);
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    this.checkQueryLength(query, 3);
    String unprocessedImageName = query[1];
    String processedImageName = query[2];
    ImageInterface unprocessedImage = this.model.getImage(unprocessedImageName);
    ImageInterface processedImage =
            new IntensityComponentGreyscaleCommand().process(unprocessedImage);
    this.model.storeImage(processedImageName, processedImage);
    this.writeMessage(
            "Successfully applied intensity component and stored as: "
                    + processedImageName + ".\n");
  }
}