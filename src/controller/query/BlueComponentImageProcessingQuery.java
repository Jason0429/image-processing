package controller.query;

import model.Image;
import model.ImageInterface;
import model.ImageProcessingModel;
import model.commands.BlueComponentGreyscaleCommand;
import model.commands.ImageProcessingCommand;
import view.ImageProcessingView;

public class BlueComponentImageProcessingQuery extends AbstractImageProcessingQueryCommand {

  public BlueComponentImageProcessingQuery(ImageProcessingModel model, ImageProcessingView view) {
    super(model, view);
  }

  @Override
  public ImageInterface getProcessedImage(
          ImageInterface unprocessedImage, String[] processParams)
          throws IllegalArgumentException {
    return new BlueComponentGreyscaleCommand().process(unprocessedImage);
  }
}