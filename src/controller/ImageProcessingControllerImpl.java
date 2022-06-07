package controller;

import model.Image;
import model.ImageProcessingModel;
import view.ImageProcessingView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class represents the controller that handles interactions for the image processing program.
 */
public class ImageProcessingControllerImpl implements ImageProcessingController {
  private final ImageProcessingModel model;
  private final ImageProcessingView view;
  private final Appendable out;

  public ImageProcessingControllerImpl(ImageProcessingModel model, ImageProcessingView view)
          throws IllegalArgumentException {
    this(model, view, System.out);
  }

  public ImageProcessingControllerImpl(ImageProcessingModel model, ImageProcessingView view,
                                       Appendable out) throws IllegalArgumentException {
    if (model == null || view == null || out == null) {
      throw new IllegalArgumentException("Inputs cannot be null");
    }
    this.model = model;
    this.view = view;
    this.out = out;
  }

  @Override
  public void start() {
  }

  /**
   * Processes an image with a specified processing method.
   *
   * @param imageName    the name of the image to process
   * @param newImageName the name for the processed image
   * @param cmd          the processing command
   * @throws IllegalArgumentException if the image name is invalid
   */
  private void process(String imageName, String newImageName, ImageProcessingCommand cmd)
          throws IllegalArgumentException {

    Image processedImg = cmd.process(this.model.getImage(imageName));
    this.model.storeImage(newImageName, processedImg);
  }

  /**
   * Exports an image as a .PPM file.
   *
   * @param filePath the location to save the image
   * @param imgName  the image name
   */
  private void exportImage(String filePath, String imgName) throws FileNotFoundException,
          IOException {

    String ppm = this.model.getImage(imgName).toPPM();
    FileOutputStream fos = new FileOutputStream(filePath);
    fos.write(ppm.getBytes());
    fos.close();
  }

  private void displayMenu() throws IOException {
    this.view.renderMessage("Options:\n");
    this.view.renderMessage("load [image-path] [image-name]\n");
    this.view.renderMessage("save [image-path] [image-name]\n");
    this.view.renderMessage("red-component [image-name] [dest-image-name]\n");
    this.view.renderMessage("green-component [image-name] [dest-image-name]\n");
    this.view.renderMessage("blue-component [image-name] [dest-image-name]\n");
    this.view.renderMessage("value-component [image-name] [dest-image-name]\n");
    this.view.renderMessage("luma-component [image-name] [dest-image-name]\n");
    this.view.renderMessage("intensity-component [image-name] [dest-image-name]\n");
    this.view.renderMessage("horizontal-flip [image-name] [dest-image-name]\n");
    this.view.renderMessage("vertical-flip [image-name] [dest-image-name]\n");
    this.view.renderMessage("brighten [image-name] [dest-image-name] [increment]");
  }
}
