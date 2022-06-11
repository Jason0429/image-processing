package controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;

import model.ImageInterface;
import model.Pixel;

import javax.imageio.ImageIO;

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
          throws IOException {
    FileOutputStream fos = new FileOutputStream(filePath);
    String ppm = image.toPPMString();
    fos.write(ppm.getBytes());
    fos.close();
  }

  public static void exportAlternate(ImageInterface image, String filePath) throws IOException {
    BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        Pixel currentPixel = image.getPixelAt(row, col);
        img.setRGB(col, row, new Color(currentPixel.getRed(), currentPixel.getGreen(),
                currentPixel.getBlue()).getRGB());
      }
    }
    ImageIO.write(img, "png", new File(filePath)); // TODO: can be any file extension we want
  }

}

