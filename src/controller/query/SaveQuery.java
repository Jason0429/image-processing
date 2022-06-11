package controller.query;

import controller.ImageExporter;
import model.Image;
import model.ImageProcessingModel;

import java.io.IOException;
import java.util.function.Consumer;

public class SaveQuery implements QueryCommand {
  private final ImageProcessingModel model;
  private final Runnable displayInvalidCommand;
  private final Consumer<String> displayMessage;

  public SaveQuery(ImageProcessingModel model,
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
      String filePath = query[0];
      String imageName = query[1];
      Image image = this.model.getImage(imageName);
      ImageExporter.export(image, filePath);
      this.displayMessage.accept("Successfully saved " + imageName + " at " + filePath + "\n");
    } catch (IllegalArgumentException | IOException e) {
      this.displayInvalidCommand.run();
    }
  }
}
