package controller;

import model.Image;
import model.ImageProcessingModel;

import java.util.function.Consumer;

public class LoadQuery implements QueryCommand {
  private final ImageProcessingModel model;
  private final Runnable displayInvalidCommand;
  private final Consumer<String> displayMessage;

  public LoadQuery(ImageProcessingModel model,
                   Runnable displayInvalidCommand,
                   Consumer<String> displayMessage) {
    this.model = model;
    this.displayInvalidCommand = displayInvalidCommand;
    this.displayMessage = displayMessage;
  }

  @Override
  public void execute(String[] query) {
    if (query.length != 2) {
      this.displayInvalidCommand.run();
      return;
    }

    try {
      String imagePath = query[0];
      String imageName = query[1];
      Image image = ImageLoader.load(imagePath);
      this.model.storeImage(imageName, image);
      this.displayMessage.accept("Successfully loaded " + imageName + " from " + imagePath + "\n");
    } catch (IllegalArgumentException e) {
      this.displayInvalidCommand.run();
    }
  }
}
