package model;

public class ImageFilter {

  public static Image filter(double[][] kernelMatrix, Image img) {
    int height = kernelMatrix.length;
    int width = kernelMatrix[0].length;
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
                (int) red,
                (int) green,
                (int) blue);
      }
    }
    return new Image(pixelMatrix, img.getMaxValue(), img.getWidth(), img.getHeight());
  }

  private static Pixel getPixelOrNull(int row, int col, Image img) {
    try {
      return img.getPixelAt(row, col);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
}
