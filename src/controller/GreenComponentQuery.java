package controller;

import model.Image;
import model.ImageProcessingModel;
import model.commands.GreenComponentGreyscaleCommand;

import java.util.function.Consumer;

public class GreenComponentQuery extends ProcessImageQueryCommand implements QueryCommand {

  public GreenComponentQuery(ImageProcessingModel model,
                             Runnable displayInvalidCommand,
                             Consumer<String> displayMessage) {
    super(model,
            (Integer numParams) -> numParams != 2,
            displayInvalidCommand,
            (String[] params, Image unprocessedImg) -> {
              if (params.length != 0) {
                displayInvalidCommand.run();
              }
              return new GreenComponentGreyscaleCommand().process(unprocessedImg);
            },
            displayMessage, "Successfully applied green component and stored as: ");
  }
}