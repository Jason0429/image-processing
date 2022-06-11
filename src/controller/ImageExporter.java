package controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import model.Image;
import model.ImageInterface;

/**
 * Represents the class that exports {@code Image}s to .ppm files.
 */
public class ImageExporter {
  /**
   * Exports the {@code Image} to a .ppm formatted file to a specific file path destination.
   *
   * @param image    the image to export
   * @param filePath the path to export the image
   */
  public static void export(ImageInterface image, String filePath)
          throws FileNotFoundException, IOException {
    FileOutputStream fos = new FileOutputStream(filePath);
    String ppm = image.toPPMString();
    fos.write(ppm.getBytes());
    fos.close();
  }
}

