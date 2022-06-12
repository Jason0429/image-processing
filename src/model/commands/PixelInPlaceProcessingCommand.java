package model.commands;

import model.ExceptionMessage;
import model.Image;
import model.ImageInterface;
import model.Pixel;

/**
 * This class represents an abstracted image processing command that handles
 * image processing for
 * processing that keeps pixels in place.
 */
public abstract class PixelInPlaceProcessingCommand implements ImageProcessingCommand {
  @Override
  public ImageInterface process(ImageInterface img) throws IllegalArgumentException {
    if (img == null) {
      throw new IllegalArgumentException(
              String.format(ExceptionMessage.SPECIFIC_NULL_ARGUMENT.toString(), "Image"));
    }
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
   * Produces a new pixel that has been processed. Specific details about how the
   * image has been
   * processed vary by implementation.
   *
   * @param pixel the pixel to be processed
   * @return the processed pixel
   * @throws IllegalArgumentException if the pixel is null
   */
  protected abstract Pixel processPixel(Pixel pixel) throws IllegalArgumentException;
}
