package controller;

import model.ImageProcessingModel;
import view.ImageProcessingView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
   * @param imageName the image to be processed
   * @param command   the processing method
   */
  private void process(String imageName, String newImageName, String command) {
    Map<String, ImageProcessingCommand> commands = new HashMap<String, ImageProcessingCommand>();
    // TODO: need to make commands have abstract class with the same constructor, can't pass
    //  arguments after instances have been created in the map
  }

  /**
   * Exports an image as a .PPM file.
   *
   * @param filePath the location to save the image
   * @param imgName  the image name
   */
  private void exportImage(String filePath, String imgName) throws FileNotFoundException,
          IOException {

    String ppm = this.model.getImage(imgName).toPPMString();
    FileOutputStream fos = new FileOutputStream(filePath);
    fos.write(ppm.getBytes());
    fos.close();
  }
}
