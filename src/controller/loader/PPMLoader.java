package controller.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.ExceptionMessage;
import model.Image;
import model.ImageInterface;
import model.Pixel;

/**
 * Represents an image loader for .ppm files.
 */
public class PPMLoader extends AbstractImageLoader {

  /**
   * Constructs a .ppm image loader with specified file path.
   *
   * @param filePath the image file path.
   */
  public PPMLoader(String filePath) {
    super(filePath);
  }

  @Override
  public ImageInterface loadHelper() throws IOException {
    try {
      Scanner sc = new Scanner(new FileInputStream(this.filePath));
      StringBuilder builder = new StringBuilder();

      // Populate string builder with non-comment lines.
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        if (!line.isEmpty() && line.charAt(0) != '#') {
          builder.append(line).append(System.lineSeparator());
        }
      }

      sc = new Scanner(builder.toString());

      // Check if file starts with P3.
      if (!sc.next().equals("P3")) {
        throw new IllegalArgumentException(ExceptionMessage.INVALID_PPM_FILE.toString());
      }

      // Get width, height, and maxValue of image.
      int width = sc.nextInt();
      int height = sc.nextInt();
      int maxValue = sc.nextInt();
      Pixel[][] pixelMatrix = new Pixel[height][width];

      // Configure pixels with RGB values from PPM file.
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          pixelMatrix[i][j] = new Pixel(
                  maxValue, sc.nextInt(), sc.nextInt(), sc.nextInt());
        }
      }

      // Should not have more values after pixels are done parsing.
      if (sc.hasNext()) {
        throw new IllegalArgumentException(ExceptionMessage.INVALID_PPM_FILE.toString());
      }

      sc.close();

      return new Image(pixelMatrix, maxValue, width, height);
    } catch (NoSuchElementException e) {
      throw new IllegalArgumentException(ExceptionMessage.INVALID_PPM_FILE.toString());
    }
  }
}
