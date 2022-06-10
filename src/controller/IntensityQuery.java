package controller;

import model.Image;
import model.ImageProcessingModel;
import model.commands.IntensityComponentGreyscaleCommand;

import java.util.function.Consumer;

class IntensityQuery extends ProcessImageQueryCommand implements QueryCommand {

  public IntensityQuery(ImageProcessingModel model,
                        Runnable displayInvalidCommand,
                        Consumer<String> displayMessage) {
    super(model,
            (Integer numParams) -> numParams != 2,
            displayInvalidCommand,
            (String[] params, Image unprocessedImg) -> {
              if (params.length != 0) {
                displayInvalidCommand.run();
              }
              return new IntensityComponentGreyscaleCommand().process(unprocessedImg);
            },
            displayMessage, "Successfully applied intensity component and stored as: ");
  }
}