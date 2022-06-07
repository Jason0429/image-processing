package model;

/**
 * This class represents an image stored in 8-bit RGB format.
 */
public class Image implements ImageInterface {
  private final Pixel[][] pixelMatrix;
  private final int maxValue;
  private final int width;
  private final int height;

  /**
   * Constructs a new image.
   *
   * @param pixelMatrix the 2-D array of pixels in the image
   * @param width       the image width
   * @param height      the image height
   */
  public Image(Pixel[][] pixelMatrix, int maxValue, int width, int height) {
    this.pixelMatrix = pixelMatrix;
    this.maxValue = maxValue;
    this.width = width;
    this.height = height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getMaxValue() {
    return this.maxValue;
  }

  @Override
  public Pixel getPixelAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= height || col < 0 || col >= width) {
      throw new IllegalArgumentException(ExceptionMessage.OUT_OF_BOUNDS.toString());
    }
    return this.pixelMatrix[row][col];
  }

  @Override
  public String toPPMString() {
    StringBuilder sb = new StringBuilder("P3").append(System.lineSeparator());

    // add width, height, and maxValue
    sb.append(this.width)
            .append(" ")
            .append(this.height)
            .append(System.lineSeparator())
            .append(this.maxValue).append(System.lineSeparator());

    // add pixels
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        Pixel currentPixel = this.getPixelAt(row, col);
        int red = currentPixel.getRed();
        int green = currentPixel.getGreen();
        int blue = currentPixel.getBlue();
        sb.append(red).append(System.lineSeparator())
                .append(green).append(System.lineSeparator())
                .append(blue).append(System.lineSeparator());
      }
    }
    return sb.toString();
  }

  @Override
  public Image copy() {
    Pixel[][] pixelMatrix = new Pixel[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        pixelMatrix[i][j] = this.getPixelAt(i, j).copy();
      }
    }
    return new Image(pixelMatrix, this.maxValue, this.width, this.height);
  }
}
