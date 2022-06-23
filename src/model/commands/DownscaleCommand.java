package model.commands;

import model.Image;
import model.ImageInterface;
import model.Pixel;

public class DownscaleCommand implements ImageProcessingCommand {
  private final int height;
  private final int width;

  public DownscaleCommand(int height, int width) {
    this.height = height;
    this.width = width;
  }

  @Override
  public ImageInterface process(ImageInterface img) throws IllegalArgumentException {
    if (this.height >= img.getHeight() || this.width >= img.getWidth()) {
      throw new IllegalArgumentException("Dimensions must be smaller to be downsized");
    }
    Pixel[][] scaledPixelMatrix = new Pixel[this.height][this.width];
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        double scaledRow = ((double) row / this.height) * img.getHeight();
        double scaledCol = ((double) col / this.width) * img.getWidth();
        Pixel topLeft = img.getPixelAt(
                (int) Math.floor(scaledRow),
                (int) Math.floor(scaledCol));
        Pixel bottomRight = img.getPixelAt(
                (int) Math.ceil(scaledRow),
                (int) Math.ceil(scaledCol));
        Pixel topRight = img.getPixelAt(
                (int) Math.floor(scaledRow),
                (int) Math.ceil(scaledCol));
        Pixel bottomLeft = img.getPixelAt(
                (int) Math.ceil(scaledRow),
                (int) Math.floor(scaledCol));
        int scaledRed =
                (topLeft.getRed() + bottomRight.getRed() + topRight.getRed() + bottomLeft.getRed()) / 4;
        int scaledGreen =
                (topLeft.getGreen() + bottomRight.getGreen() + topRight.getGreen() + bottomLeft.getGreen()) / 4;
        int scaledBlue =
                (topLeft.getBlue() + bottomRight.getBlue() + topRight.getBlue() + bottomLeft.getBlue()) / 4;
        int scaledAlpha =
                (topLeft.getAlpha() + bottomRight.getAlpha() + topRight.getAlpha() + bottomLeft.getAlpha()) / 4;
        scaledPixelMatrix[row][col] = new Pixel(img.getMaxValue(), scaledRed, scaledGreen,
                scaledBlue, scaledAlpha);
      }
    }
    return new Image(scaledPixelMatrix, img.getMaxValue(), this.width, this.height);
  }
}
