package controller.query;

import model.Image;
import model.ImageProcessingModel;
import model.commands.BlueComponentGreyscaleCommand;

import java.util.function.Consumer;

public class BlueComponentQuery extends ProcessImageQueryCommand implements QueryCommand {

  public BlueComponentQuery(ImageProcessingModel model,
                            Runnable displayInvalidCommand,
                            Consumer<String> displayMessage) {
    super(model,
            (Integer numParams) -> numParams != 2,
            displayInvalidCommand,
            (String[] params, Image unprocessedImg) -> {
              if (params.length != 0) {
                displayInvalidCommand.run();
              }
              return new BlueComponentGreyscaleCommand().process(unprocessedImg);
            },
            displayMessage, "Successfully applied blue component and stored as: ");
  }
}