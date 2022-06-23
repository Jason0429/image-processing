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
public class ImageMaskQuery extends AbstractQueryCommand implements QueryCommand {

  private final Map<String, ImageProcessingCommand> cmdMap;

  /**
   * Constructs a new image mask query.
   *
   * @param model the model
   * @param view  the view
   */
  public ImageMaskQuery(ImageProcessingModel model, ImageProcessingTextView view) {
    super(model, view);
    this.cmdMap = Utils.getCommandMap();
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    // needs to take in mask location, filter type, and filter args if any
    // mask image-name new-image-name mask-location filter args
    this.checkQueryLengthGreaterEqual(query, 5);
    String unprocessedImageName = query[1];
    String processedImageName = query[2];
    String maskName = query[3];
    String filter = query[4];
    String[] filterArgs = Arrays.copyOfRange(query, 5, query.length);

    ImageInterface unprocessedImage = this.model.getImage(unprocessedImageName);
    ImageInterface maskImage = this.model.getImage(maskName);
    ImageInterface processedImage;

    if (filter.equals("brighten")) {
      if (filterArgs.length != 1) {
        throw new IllegalArgumentException("Invalid parameters specified for brighten");
      } else {
        int value = Integer.parseInt(filterArgs[0]);
        processedImage = new ImageMaskCommand(maskImage,
                new BrightenCommand(value)).process(unprocessedImage);
      }
    } else {
      ImageProcessingCommand cmd = this.cmdMap.getOrDefault(filter, null);
      if (cmd != null) {
        processedImage = new ImageMaskCommand(maskImage,
                cmd).process(unprocessedImage);
      } else {
        throw new IllegalArgumentException("Invalid command specified");
      }
    }
    if (processedImage != null) {
      this.model.storeImage(processedImageName, processedImage);
      this.writeMessage(
              String.format("Successfully applied mask with filter %s and stored as: " + processedImageName + ".\n", filter));
    } else {
      throw new IllegalArgumentException("Failed to apply mask");
    }
  }

  /**
   * Throws an exception if the query length is not correct.
   *
   * @param length the set number of allowed tokens in the query.
   * @throws IllegalArgumentException if the query length does not equal the given length.
   */
  private void checkQueryLengthGreaterEqual(String[] query, int length) throws IllegalArgumentException {
    if (query.length < length) {
      throw new IllegalArgumentException(ExceptionMessage.INVALID_COMMAND_PARAMETERS.toString());
    }
  }
}
