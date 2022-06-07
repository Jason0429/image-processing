package controller;

import model.Image;
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
  private final Map<String, ImageProcessingCommand> commands;

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

    this.commands = new HashMap<String, ImageProcessingCommand>();
    this.commands.put("brighten", new BrightenParamCommand());
    this.commands.put("darken", new DarkenCommand());
    this.commands.put("flip-horizontal", new FlipHorizontalCommand());
    this.commands.put("flip-vertical", new FlipVerticalCommand());
    this.commands.put("red-component", new RedComponentGreyscaleCommand());
    this.commands.put("green-component", new GreenComponentGreyscaleCommand());
    this.commands.put("blue-component", new BlueComponentGreyscaleCommand());
    this.commands.put("value-component", new ValueComponentGreyscaleCommand());
    this.commands.put("luma-component", new LumaComponentGreyscaleCommand());
    this.commands.put("intensity-component", new IntensityComponentGreyscaleCommand());
  }

  @Override
  public void start() {

  }

  /**
   * Processes an image with a specified processing method and saves it with the specified name.
   *
   * @param imageName the image to be processed
   * @param newImageName the name for the new image
   * @param command the processing method
   * @param parameters the parameters for the processing method, if any
   */
  private void process(String imageName, String newImageName, String command, int ...parameters)
          throws IllegalArgumentException {
    ImageProcessingCommand cmd = this.commands.getOrDefault(command, null);
    if (cmd != null) {
      Image processedImg = cmd.process(this.model.getImage(imageName), parameters);
      this.model.storeImage(newImageName, processedImg);
    }
  }

  /**
   * Exports an image as a .PPM file.
   *
   * @param filePath the location to save the image
   * @param imgName the image name
   */
  private void exportImage(String filePath, String imgName) throws FileNotFoundException,
          IOException {

    String ppm = this.model.getImage(imgName).toPPM();
    FileOutputStream fos = new FileOutputStream(filePath);
    fos.write(ppm.getBytes());
    fos.close();
  }
}
