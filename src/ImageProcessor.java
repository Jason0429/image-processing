import model.ImageLoader;

/**
 * This class represents an image processor program that can import, manipulate, and export .PPM
 * files.
 */
public class ImageProcessor {

  /**
   * Starts the program.
   *
   * @param args TODO: NEEDS TO BE COMPLETED
   */
  public static void main(String[] args) {
    ImageLoader loader = new ImageLoader();
    loader.getImage("images/Koala.ppm");
  }
}
