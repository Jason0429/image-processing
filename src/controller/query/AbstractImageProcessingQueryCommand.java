package controller.query;

import java.io.IOException;
import java.util.Arrays;

import model.ExceptionMessage;
import model.ImageInterface;
import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * Represents the abstract class for an image processing query command.
 */
public abstract class AbstractImageProcessingQueryCommand implements QueryCommand {
  protected ImageProcessingModel model;
  protected ImageProcessingView view;

  /**
   * Constructs an abstract query command.
   *
   * @param model the image processing model for image storage.
   * @param view  the image processing view responsible for rendering output.
   */
  public AbstractImageProcessingQueryCommand(
          ImageProcessingModel model, ImageProcessingView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void execute(String[] query) {
    try {
      if (query.length < 3) {
        throw new IllegalArgumentException(ExceptionMessage.INVALID_COMMAND_PARAMETERS.toString());
      }
      ImageInterface unprocessedImage = this.model.getImage(query[1]);
      String processedImageName = query[2];
      ImageInterface processedImage = this.getProcessedImage(
              unprocessedImage, Arrays.copyOfRange(query, 3, query.length));
      this.model.storeImage(processedImageName, processedImage);
    } catch (IllegalArgumentException e) {
      this.writeMessage(e.getMessage());
    }
  }

  /**
   * Wrapper method for rendering message to view.
   *
   * @param message the message to be rendered.
   * @throws IllegalStateException if an IOException is thrown.
   */
  private void writeMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Failed to display message");
    }
  }

  /**
   * Throws an exception if the query length is not correct.
   *
   * @param length the set number of allowed tokens in the query.
   * @throws IllegalArgumentException if the query length does not equal the given length.
   */
  protected void checkQueryLength(String[] query, int length) throws IllegalArgumentException {
    if (query.length != length) {
      throw new IllegalArgumentException(ExceptionMessage.INVALID_COMMAND_PARAMETERS.toString());
    }
  }

  /**
   * Takes in query and produces processed image.
   *
   * @param unprocessedImage the unprocessed image to be processed.
   * @param processParams    the parameters needed (or not needed) to process an image.
   * @return the processed image.
   * @throws IllegalArgumentException if query is invalid.
   */
  protected abstract ImageInterface getProcessedImage(
          ImageInterface unprocessedImage, String[] processParams)
          throws IllegalArgumentException;
}
