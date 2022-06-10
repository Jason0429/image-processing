package controller;

import model.Image;
import model.ImageProcessingModel;
import model.commands.LumaComponentGreyscaleCommand;

import java.util.function.Consumer;

public class LumaQuery extends ProcessImageQueryCommand implements QueryCommand {

  public LumaQuery(ImageProcessingModel model,
                   Runnable displayInvalidCommand,
                   Consumer<String> displayMessage) {
    super(model,
            (Integer numParams) -> numParams != 2,
            displayInvalidCommand,
            (String[] params, Image unprocessedImg) -> {
              if (params.length != 0) {
                displayInvalidCommand.run();
              }
              return new LumaComponentGreyscaleCommand().process(unprocessedImg);
            },
            displayMessage, "Successfully applied luma component and stored as: ");
  }
}