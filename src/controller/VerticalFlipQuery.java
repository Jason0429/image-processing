package controller;

import model.Image;
import model.ImageProcessingModel;
import model.commands.FlipVerticalCommand;

import java.util.function.Consumer;

class VerticalFlipQuery extends ProcessImageQueryCommand implements QueryCommand {

  public VerticalFlipQuery(ImageProcessingModel model,
                           Runnable displayInvalidCommand,
                           Consumer<String> displayMessage) {
    super(model,
            (Integer numParams) -> numParams != 2,
            displayInvalidCommand,
            (String[] params, Image unprocessedImg) -> {
              if (params.length != 0) {
                displayInvalidCommand.run();
              }
              return new FlipVerticalCommand().process(unprocessedImg);
            },
            displayMessage, "Successfully flipped image vertically and stored as: ");
  }
}
