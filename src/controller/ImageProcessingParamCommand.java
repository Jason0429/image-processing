package controller;

import model.Image;
import model.Pixel;

/**
 * This class represents an abstracted image processing command that handles image processing for
 * processing that keeps pixels in place.
 */
public abstract class ImageProcessingParamCommand implements ImageProcessingCommand {
  @Override
  public Image process(Image img, int... parameters) throws IllegalArgumentException {
    Pixel[][] pixelArray = new Pixel[img.getHeight()][img.getWidth()];
    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        Pixel currentPixel = img.getPixelAt(row, col);
        pixelArray[row][col] = this.processPixel(currentPixel);
      }
    }

    return new Image(pixelArray, img.getMaxValue(), img.getWidth(), img.getHeight());
  }

  /**
   * Produces a new pixel that has been processed. Specific details about how the image has been
   * processed vary by implementation.
   *
   * @param pixel the pixel to be processed
   * @param parameters the parameters for processing the pixel, if needed
   * @return the processed pixel
   * @throws IllegalArgumentException if the pixel is null, or invalid parameters are passed
   */
  abstract Pixel processPixel(Pixel pixel, int ...parameters) throws IllegalArgumentException;
}
