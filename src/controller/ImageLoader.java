package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.ExceptionMessage;
import model.Image;
import model.Pixel;

/**
 * This class reads .PPM files and produces new Image objects.
 */
public class ImageLoader {

  /**
   * Loads a .PPM file with 24-bit color and creates a new image.
   *
   * @param fileLocation the .PPM file location
   * @return a new {@code Image} object
   * @throws IllegalArgumentException if the file cannot be found
   */
  public static Image load(String fileLocation) throws IllegalArgumentException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(fileLocation));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException(ExceptionMessage.INVALID_FILE_PATH.toString());
    }

    StringBuilder builder = new StringBuilder();
    // read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      try {
        if (s.charAt(0) != '#') {
          builder.append(s).append(System.lineSeparator());
        }
      } catch (StringIndexOutOfBoundsException e) {
        throw new IllegalArgumentException(ExceptionMessage.INVALID_PPM_FILE.toString());
      }
    }
    sc.close();

    // now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    // Check if file is a valid PPM file.
    String token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException(ExceptionMessage.INVALID_PPM_FILE.toString());
    }

    try {
      int width = sc.nextInt();
      int height = sc.nextInt();
      int maxValue = sc.nextInt();
      Pixel[][] pixelArray = new Pixel[height][width];


      // Configure pixels with RGB values from PPM file.
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          pixelArray[i][j] = new Pixel(maxValue, sc.nextInt(), sc.nextInt(), sc.nextInt());
        }
      }
      if (!sc.hasNext()) {
        sc.close();
        return new Image(pixelArray, maxValue, width, height);
      } else {
        throw new IllegalArgumentException(ExceptionMessage.INVALID_PPM_FILE.toString());
      }
    } catch (ArrayIndexOutOfBoundsException | NoSuchElementException | IllegalStateException e) {
      throw new IllegalArgumentException(ExceptionMessage.INVALID_PPM_FILE.toString());
    }
  }
}
