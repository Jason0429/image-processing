package controller.exporter;

import java.io.FileOutputStream;
import java.io.IOException;

import model.ImageInterface;

/**
 * Represents an image exporter for .ppm files.
 */
public class PPMExporter extends AbstractImageExporter implements ImageExporterInterface {

  /**
   * Constructs an image exporter with given image and specified file path.
   *
   * @param image    the image to export.
   * @param filePath the file path.
   */
  public PPMExporter(ImageInterface image, String filePath) {
    super(image, filePath);
  }

  @Override
  public void exportHelper() throws IOException {
    FileOutputStream fos = new FileOutputStream(this.filePath);
    String ppm = image.toPPMString();
    fos.write(ppm.getBytes());
    fos.close();
  }
}
