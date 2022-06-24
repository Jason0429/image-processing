package controller.query;

import model.ExceptionMessage;
import model.ImageInterface;
import model.ImageProcessingModel;
import model.Utils;
import model.commands.BrightenCommand;
import model.commands.ImageMaskCommand;
import model.commands.ImageProcessingCommand;
import view.text.ImageProcessingTextView;

import java.util.Arrays;
import java.util.Map;

/**
 * Represents the image mask query command.
 */
public class ImageMaskQuery extends AbstractQueryCommand {

  private final Map<String, ImageProcessingCommand> cmdMap;

  /**
   * Constructs a new image mask query.
   *
   * @param model the model
   * @param view  the view
   */
  public ImageMaskQuery(ImageProcessingModel model, ImageProcessingTextView view) {
    super(model, view);
    this.cmdMap = Utils.getTextViewCommandMap();
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    String commandType = query[0];
    String sourceImageName = query[1];
    String maskImageName = query[2];
    String destImageName = query[3];
    String[] filterArgs = Arrays.copyOfRange(query, 4, query.length);

    ImageProcessingCommand cmd = this.cmdMap.getOrDefault(commandType, null);

    if (cmd == null) {
      throw new IllegalArgumentException(ExceptionMessage.INVALID_COMMAND_PARAMETERS.toString());
    }

    switch (commandType) {
      case "brighten":
        if (filterArgs.length != 1) {
          throw new IllegalArgumentException("Invalid parameters specified for brighten");
        }
        try {
          int value = Integer.parseInt(filterArgs[0]);
          ImageInterface maskImage = this.model.getImage(maskImageName);
          ImageInterface sourceImage = this.model.getImage(sourceImageName);
          ImageInterface destImage = new ImageMaskCommand(maskImage,
                  new BrightenCommand(value)).process(sourceImage);
          this.model.storeImage(destImageName, destImage);
        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Brighten value must be an integer");
        } catch (IllegalArgumentException e) {
          throw new IllegalArgumentException("Failed to apply mask.");
        }
        break;
      default:
        try {
          ImageInterface maskImage = this.model.getImage(maskImageName);
          ImageInterface sourceImage = this.model.getImage(sourceImageName);
          ImageInterface destImage = new ImageMaskCommand(maskImage, cmd).process(sourceImage);
          this.model.storeImage(destImageName, destImage);
        } catch (IllegalArgumentException e) {
          throw new IllegalArgumentException("Failed to apply mask.");
        }
        break;
    }

    this.writeMessage(String.format("Successfully applied mask with filter %s and stored as: "
            + destImageName + ".\n", commandType));
  }
}
