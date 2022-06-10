package controller;

import model.Image;
import model.ImageProcessingModel;
import model.commands.GaussianBlurCommand;

import java.util.function.Consumer;

public class GaussianBlurQuery extends ProcessImageQueryCommand {

  public GaussianBlurQuery(ImageProcessingModel model,
                           Runnable displayInvalidCommand,
                           Consumer<String> displayMessage) {
    super(model,
            (Integer numParams) -> numParams != 2,
            displayInvalidCommand,
            (String[] params, Image unprocessedImg) -> {
              if (params.length != 0) {
                displayInvalidCommand.run();
              }
              return new GaussianBlurCommand().process(unprocessedImg);
            },
            displayMessage, "Successfully applied gaussian blur and stored as: ");
  }
}
