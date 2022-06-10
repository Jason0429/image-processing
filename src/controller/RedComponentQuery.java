package controller;

import model.Image;
import model.ImageProcessingModel;
import model.commands.RedComponentGreyscaleCommand;

import java.util.function.Consumer;

class RedComponentQuery extends ProcessImageQueryCommand implements QueryCommand {

  public RedComponentQuery(ImageProcessingModel model,
                           Runnable displayInvalidCommand,
                           Consumer<String> displayMessage) {
    super(model,
            (Integer numParams) -> numParams != 2,
            displayInvalidCommand,
            (String[] params, Image unprocessedImg) -> {
              if (params.length != 0) {
                displayInvalidCommand.run();
              }
              return new RedComponentGreyscaleCommand().process(unprocessedImg);
            },
            displayMessage, "Successfully applied red component and stored as: ");
  }
}