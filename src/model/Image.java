package model;

/**
 * This class represents an image stored in 8-bit RGB format.
 */
public class Image implements ImageInterface {
  private final Pixel[][] pixelArray;
  private final int width;
  private final int height;

  /**
   * Constructs a new image.
   * @param pixelArray the 2-D array of pixels in the image
   * @param width the image width
   * @param height the image height
   */
  public Image(Pixel[][] pixelArray, int width, int height) {
    this.pixelArray = pixelArray;
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
  public Pixel getPixelAt(int row, int col) throws IllegalArgumentException {
    return null;
  }

  @Override
  public String toPPM() {
    return null;
  }
}
