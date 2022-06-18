package controller.exporter;

import java.awt.*;
import java.awt.image.BufferedImage;

import model.ExceptionMessage;
import model.ImageInterface;
import model.Pixel;

/**
 * Represents the class that exports {@code ImageInterface} objects to image
 * files.
 */
public final class ImageExporter {
  /**
   * Exports the {@code ImageInterface} to a formatted file to a specific file
   * path destination.
   *
   * @param image    image the image to be exported.
   * @param filePath the image file path.
   * @throws IllegalArgumentException if image file cannot be saved successfully.
   */
  public static void export(ImageInterface image, String filePath) throws IllegalArgumentException {
    String fileExtension = filePath.substring(filePath.lastIndexOf('.') + 1);
    ImageExporterInterface exporter = null;
    switch (fileExtension) {
      case "ppm":
        exporter = new PPMExporter(image, filePath);
        break;
      case "png":
        exporter = new ImageIOExporter(image, filePath, BufferedImage.TYPE_INT_ARGB);
        break;
      case "bmp":
      case "jpeg":
      case "jpg":
        exporter = new ImageIOExporter(image, filePath);
        break;
      default:
        throw new IllegalArgumentException(ExceptionMessage.UNSUPPORTED_FILE_TYPE.toString());
    }
    exporter.export();
  }

  public static BufferedImage convertBuffered(ImageInterface image) {
    BufferedImage img = new BufferedImage(image.getWidth(),
            image.getHeight(), BufferedImage.TYPE_INT_ARGB);
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        Pixel currentPixel = image.getPixelAt(row, col);
        int rgb = new Color(currentPixel.getRed(), currentPixel.getGreen(),
                currentPixel.getBlue(), currentPixel.getAlpha()).getRGB();
        img.setRGB(col, row, rgb);
      }
    }
    return img;
  }
}
