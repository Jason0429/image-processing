package controller.query;

import model.Image;
import model.ImageProcessingModel;
import model.commands.ValueComponentGreyscaleCommand;

import java.util.function.Consumer;

public class ValueQuery extends ProcessImageQueryCommand implements QueryCommand {

  public ValueQuery(ImageProcessingModel model,
                    Runnable displayInvalidCommand,
                    Consumer<String> displayMessage) {
    super(model,
            (Integer numParams) -> numParams != 2,
            displayInvalidCommand,
            (String[] params, Image unprocessedImg) -> {
              if (params.length != 0) {
                displayInvalidCommand.run();
              }
              return new ValueComponentGreyscaleCommand().process(unprocessedImg);
            },
            displayMessage, "Successfully applied value component and stored as: ");
  }
}
