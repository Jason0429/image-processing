package controller;

import model.Image;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Represents the class that exports {@code Image}s to .ppm files.
 */
public class ImageExporter {
  /**
   * Exports the {@code Image} to a .ppm formatted file to a specific file path destination.
   */
  public static void export(Image image, String filePath)
          throws FileNotFoundException, IOException {
    FileOutputStream fos = new FileOutputStream(filePath);
    String ppm = image.toPPMString();
    fos.write(ppm.getBytes());
    fos.close();
  }
}

