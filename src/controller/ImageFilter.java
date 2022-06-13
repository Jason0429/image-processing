package controller;

import model.Image;
import model.ImageInterface;
import model.Pixel;

/**
 * Represents an image filter that can apply filters given a specific kernel.
 */
public class ImageFilter {

  public static ImageInterface filter(double[][] kernelMatrix, ImageInterface img)
      throws IllegalArgumentException {
    int height = kernelMatrix.length;
    int width = kernelMatrix[0].length;
    if (height % 2 == 0 || width % 2 == 0) {
      throw new IllegalArgumentException("Kernel must have odd dimensions");
    }

    Pixel[][] pixelMatrix = new Pixel[img.getHeight()][img.getWidth()];
    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        double red = 0;
        double green = 0;
        double blue = 0;
        for (int i = 0; i < height; i++) {
          for (int j = 0; j < width; j++) {
            int y = row - height / 2 + i;
            int x = col - width / 2 + j;
            Pixel currentPixel = getPixelOrNull(y, x, img);
            if (currentPixel != null) {
              red += kernelMatrix[i][j] * currentPixel.getRed();
              green += kernelMatrix[i][j] * currentPixel.getGreen();
              blue += kernelMatrix[i][j] * currentPixel.getBlue();
            }
          }
        }
        pixelMatrix[row][col] = new Pixel(img.getMaxValue(),
            (int) Math.max(0, Math.min(img.getMaxValue(), red)),
            (int) Math.max(0, Math.min(img.getMaxValue(), green)),
            (int) Math.max(0, Math.min(img.getMaxValue(), blue)));
      }
    }
    return new Image(pixelMatrix, img.getMaxValue(), img.getWidth(), img.getHeight());
  }

  /**
   * Returns the pixel at a location if it exists, and null otherwise.
   *
   * @param row the row index of the pixel.
   * @param col the column index of the pixel.
   * @param img the image reference.
   * @return the pixel at the specified location, or null.
   */
  private static Pixel getPixelOrNull(int row, int col, ImageInterface img) {
    try {
      return img.getPixelAt(row, col);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
}
