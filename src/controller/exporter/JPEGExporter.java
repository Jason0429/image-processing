package controller.exporter;

import model.ImageInterface;

public class JPEGExporter extends AbstractImageExporter {
  public JPEGExporter(ImageInterface image, String filePath) {
    super(image, filePath);
  }
}
