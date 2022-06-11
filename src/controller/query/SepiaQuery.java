package controller.query;

import model.Image;
import model.ImageProcessingModel;
import model.commands.SepiaProcessingCommand;

import java.util.function.Consumer;

public class SepiaQuery extends ProcessImageQueryCommand implements QueryCommand {

  public SepiaQuery(ImageProcessingModel model,
                    Runnable displayInvalidCommand,
                    Consumer<String> displayMessage) {
    super(model,
            (Integer numParams) -> numParams != 2,
            displayInvalidCommand,
            (String[] params, Image unprocessedImg) -> {
              if (params.length != 0) {
                displayInvalidCommand.run();
              }
              return new SepiaProcessingCommand().process(unprocessedImg);
            },
            displayMessage, "Successfully applied sepia and stored as: ");
  }
}
