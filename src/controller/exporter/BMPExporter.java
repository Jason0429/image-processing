package controller.exporter;

import java.awt.image.BufferedImage;

import model.ImageInterface;

public class BMPExporter extends AbstractImageExporter {
  public BMPExporter(ImageInterface image, String filePath) {
    super(image, filePath, BufferedImage.TYPE_4BYTE_ABGR);
  }
}
