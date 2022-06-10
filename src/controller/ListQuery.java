package controller;

import model.ImageProcessingModel;

import java.util.function.Consumer;

public class ListQuery implements QueryCommand {
  private final ImageProcessingModel model;
  private final Consumer<String> displayMessage;

  public ListQuery(ImageProcessingModel model, Consumer<String> displayMessage) {
    this.model = model;
    this.displayMessage = displayMessage;
  }

  @Override
  public void execute(String[] query) {
    // TODO: check if query has to be empty?
    String[] imageNames = this.model.getImageNames();
    if (imageNames.length == 0) {
      this.displayMessage.accept("There are no images stored at the moment.\n");
      return;
    }

    for (String imageName : this.model.getImageNames()) {
      this.displayMessage.accept(imageName + "\n");
    }
  }
}
