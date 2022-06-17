package controller.exporter;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.ImageInterface;
import model.Pixel;

public class BMPExporter extends AbstractImageExporter {
  public BMPExporter(ImageInterface image, String filePath) {
    super(image, filePath, BufferedImage.TYPE_4BYTE_ABGR);
  }
}
