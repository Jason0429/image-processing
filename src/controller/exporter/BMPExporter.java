package controller.exporter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.ImageInterface;
import model.Pixel;

public class BMPExporter extends AbstractImageExporter {
  public BMPExporter(ImageInterface image, String filePath) {
    super(image, filePath);
  }

  @Override
  protected void exportHelper() throws IOException {
    BufferedImage img = new BufferedImage(this.image.getWidth(), this.image.getHeight(),
            BufferedImage.TYPE_4BYTE_ABGR);
    for (int row = 0; row < this.image.getHeight(); row++) {
      for (int col = 0; col < this.image.getWidth(); col++) {
        Pixel currentPixel = this.image.getPixelAt(row, col);
        int rgb = new Color(currentPixel.getRed(), currentPixel.getGreen(),
                currentPixel.getBlue()).getRGB();
        img.setRGB(col, row, rgb);
      }
    }
    ImageIO.write(img, "bmp", new File(this.filePath));
  }
}
