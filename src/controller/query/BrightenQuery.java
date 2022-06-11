package controller.query;

import model.Image;
import model.ImageProcessingModel;
import model.commands.BrightenCommand;

import java.util.function.Consumer;

public class BrightenQuery extends ProcessImageQueryCommand implements QueryCommand {

  public BrightenQuery(ImageProcessingModel model,
                       Runnable displayInvalidCommand,
                       Consumer<String> displayMessage) {
    super(model,
            (Integer numParams) -> numParams != 3,
            displayInvalidCommand,
            (String[] params, Image unprocessedImg) -> {
              if (params.length != 1) {
                displayInvalidCommand.run();
              }
              int change = Integer.parseInt(params[0]);
              return new BrightenCommand(change).process(unprocessedImg);
            },
            displayMessage, "Successfully brightened image and stored as: ");
  }
}
