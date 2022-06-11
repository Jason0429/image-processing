package controller.query;

import model.Image;
import model.ImageProcessingModel;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class ProcessImageQueryCommand implements QueryCommand {
  private final ImageProcessingModel model;
  private final Function<Integer, Boolean> invalidQuery;
  private final Runnable displayInvalidCommand;
  private final BiFunction<String[], Image, Image> createProcessedImage;
  private final Consumer<String> displayMessage;
  private final String successMessage;

  public ProcessImageQueryCommand(ImageProcessingModel model,
                                  Function<Integer, Boolean> invalidQuery,
                                  Runnable displayInvalidCommand,
                                  BiFunction<String[], Image, Image> createProcessedImage,
                                  Consumer<String> displayMessage,
                                  String successMessage) {
    this.model = model;
    this.invalidQuery = invalidQuery;
    this.displayInvalidCommand = displayInvalidCommand;
    this.createProcessedImage = createProcessedImage;
    this.displayMessage = displayMessage;
    this.successMessage = successMessage;
  }

  @Override
  public void execute(String[] query) {
    if (this.invalidQuery.apply(query.length)) {
      this.displayInvalidCommand.run();
      return;
    }

    try {
      String imageName = query[0];
      String destImageName = query[1];
      Image unprocessedImage = this.model.getImage(imageName);
      Image processedImage = this.createProcessedImage.apply(
              query.length > 2 ? Arrays.copyOfRange(query, 2, query.length) : new String[0],
              unprocessedImage);
      this.model.storeImage(destImageName, processedImage);
      this.displayMessage.accept(successMessage + destImageName + "\n");
    } catch (IllegalArgumentException e) {
      this.displayInvalidCommand.run();
    }
  }
}
