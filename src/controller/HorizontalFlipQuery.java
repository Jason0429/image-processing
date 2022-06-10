package controller;

import model.Image;
import model.ImageProcessingModel;
import model.commands.FlipHorizontalCommand;

import java.util.function.Consumer;

class HorizontalFlipQuery extends ProcessImageQueryCommand implements QueryCommand {

  public HorizontalFlipQuery(ImageProcessingModel model,
                             Runnable displayInvalidCommand,
                             Consumer<String> displayMessage) {
    super(model,
            (Integer numParams) -> numParams != 2,
            displayInvalidCommand,
            (String[] params, Image unprocessedImg) -> {
              if (params.length != 0) {
                displayInvalidCommand.run();
              }
              return new FlipHorizontalCommand().process(unprocessedImg);
            },
            displayMessage, "Successfully flipped image horizontally and stored as: ");
  }
}
