package controller.exporter;

import java.awt.image.BufferedImage;

import model.ImageInterface;

public class PNGExporter extends AbstractImageExporter {
  public PNGExporter(ImageInterface image, String filePath) {
    super(image, filePath, BufferedImage.TYPE_INT_ARGB);
  }
}
