package controller;

import model.Image;
import model.ImageProcessingModel;
import model.commands.SharpenCommand;

import java.util.function.Consumer;

public class SharpenQuery extends ProcessImageQueryCommand {

  public SharpenQuery(ImageProcessingModel model,
                      Runnable displayInvalidCommand,
                      Consumer<String> displayMessage) {
    super(model,
            (Integer numParams) -> numParams != 2,
            displayInvalidCommand,
            (String[] params, Image unprocessedImg) -> {
              if (params.length != 0) {
                displayInvalidCommand.run();
              }
              return new SharpenCommand().process(unprocessedImg);
            },
            displayMessage, "Successfully sharpened image and stored as: ");
  }
}
