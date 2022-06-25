package model.commands;

import model.Image;
import model.ImageInterface;
import model.Pixel;

/**
 * Represents a command to apply a mask over an image and process it.
 */
public class ImageMaskCommand implements ImageProcessingCommand {
  private final ImageInterface maskImage;
  private final ImageProcessingCommand cmd;

  /**
   * Construct a new image mask command.
   *
   * @param maskImage the mask
   * @param cmd       the command to be applied
   */
  public ImageMaskCommand(ImageInterface maskImage,
                          ImageProcessingCommand cmd) {
    this.maskImage = maskImage;
    this.cmd = cmd;
  }

  @Override
  public ImageInterface process(ImageInterface img) throws IllegalArgumentException {
    if (this.maskImage.getWidth() != img.getWidth()
            || this.maskImage.getHeight() != img.getHeight()) {
      throw new IllegalArgumentException("Image dimensions must match mask dimensions");
    }
    Pixel[][] maskedPixelMatrix = new Pixel[img.getHeight()][img.getWidth()];
    ImageInterface processedImage = this.cmd.process(img);
    for (int row = 0; row < this.maskImage.getHeight(); row++) {
      for (int col = 0; col < this.maskImage.getWidth(); col++) {
        Pixel currentMaskPixel = this.maskImage.getPixelAt(row, col);
        if (currentMaskPixel.getRed() == 0
                && currentMaskPixel.getGreen() == 0
                && currentMaskPixel.getBlue() == 0) {
          maskedPixelMatrix[row][col] = processedImage.getPixelAt(row, col);
        } else {
          maskedPixelMatrix[row][col] = img.getPixelAt(row, col);
        }
      }
    }
    return new Image(maskedPixelMatrix, img.getMaxValue(), img.getWidth(), img.getHeight());
  }
}
