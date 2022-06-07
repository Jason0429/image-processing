package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class reads .PPM files and produces new Image objects.
 */
public class ImageLoader {

  /**
   * Constructs a new ImageLoader.
   */
  public ImageLoader() { }

  /**
   * Loads a .PPM file with 24-bit color and creates a new image.
   *
   * @param fileLocation the .PPM file location
   * @return a new Image object
   * @throws IllegalArgumentException if the file cannot be found or is invalid
   */
  public Image getImage(String fileLocation) throws IllegalArgumentException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(fileLocation));
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Invalid file location");
    }

    StringBuilder builder = new StringBuilder();
    // read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    // now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    try {
      int width = sc.nextInt();
      int height = sc.nextInt();
      int maxValue = sc.nextInt();
      Pixel[][] pixelArray = new Pixel[height][width];

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          pixelArray[i][j] = new Pixel(maxValue, sc.nextInt(), sc.nextInt(), sc.nextInt());
        }
      }
      return new Image(pixelArray, maxValue, width, height);
    } catch (IllegalStateException e) {
      throw new IllegalArgumentException("Invalid PPM file");
    }
  }
}
