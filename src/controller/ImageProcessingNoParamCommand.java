package controller;

import model.Image;


/**
 * This class represents an abstracted image processing command that handles image processing for
 * processing that keeps pixels in place that does not take parameters.
 */
public abstract class ImageProcessingNoParamCommand extends ImageProcessingParamCommand {

  /**
   * Produces a processed version of the image.
   *
   * @param img        the image to be processed
   * @param parameters should not have any parameters
   * @return a processed version of the image.
   * @throws IllegalArgumentException if the image is null, or if parameters are passed
   */
  @Override
  public Image process(Image img, int... parameters) throws IllegalArgumentException {
    if (parameters.length > 0) {
      throw new IllegalArgumentException("This command does not accept parameters");
    }
    return super.process(img);
  }
}
