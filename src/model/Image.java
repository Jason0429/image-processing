package model;

/**
 * This class represents an image stored in 8-bit RGB format.
 */
public class Image implements ImageInterface {
  private final Pixel[][] pixelArray;
  private final int maxValue;
  private final int width;
  private final int height;

  /**
   * Constructs a new image.
   * @param pixelArray the 2-D array of pixels in the image
   * @param width the image width
   * @param height the image height
   */
  public Image(Pixel[][] pixelArray, int maxValue, int width, int height) {
    this.pixelArray = pixelArray;
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
      throw new IllegalArgumentException("Location out of bounds");
    }
    return this.pixelArray[row][col]; // TODO: should this be returning a reference?
  }

  @Override
  public String toPPM() {
    StringBuilder sb = new StringBuilder("P3\n");

    // add width, height
    sb.append(this.width).append(" ").append(this.height).append("\n");

    // add maxValue
    sb.append(this.maxValue).append("\n");

    // add pixels
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        Pixel currentPixel = this.pixelArray[row][col];
        sb.append(" ").append(currentPixel.getRed())
          .append(" ").append(currentPixel.getGreen())
          .append(" ").append(currentPixel.getBlue()).append(" ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
