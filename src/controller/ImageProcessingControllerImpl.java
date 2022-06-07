package controller;

import model.Image;
import model.ImageLoader;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingTextView;
import view.ImageProcessingView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class represents the controller that handles interactions for the image processing program.
 */
public class ImageProcessingControllerImpl implements ImageProcessingController {
  private final ImageProcessingModel model;
  private final ImageProcessingView view;
  private final Scanner in;

  public ImageProcessingControllerImpl(ImageProcessingModel model, ImageProcessingView view) {
    this(model, view, new Scanner(System.in));
  }

  public ImageProcessingControllerImpl(ImageProcessingModel model, ImageProcessingView view,
                                       Scanner in)
          throws IllegalArgumentException {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("Inputs cannot be null");
    }
    this.model = model;
    this.view = view;
    this.in = in;
  }

  @Override
  public void start() throws IllegalStateException {
    boolean quit = false;
    while (!quit) {
      String[] nextLine = in.nextLine().split(" ");
      if (nextLine.length > 0) {
        ImageProcessingCommand cmd = null;
        String imageName = null;
        String newImageName = null;
        switch (nextLine[0]) {
          case "menu":
            this.displayOptions();
            break;
          case "load":
            try {
              ImageLoader loader = new ImageLoader();
              this.model.storeImage(nextLine[2], loader.getImage(nextLine[1]));
            } catch (ArrayIndexOutOfBoundsException|IllegalArgumentException e) {
              this.displayMessage("Invalid parameters specified, please try again.\n");
            }
            break;
          case "save":
            try {
              this.exportImage(nextLine[1], nextLine[2]);
            } catch (ArrayIndexOutOfBoundsException|FileNotFoundException e) {
              this.displayMessage("Invalid parameters specified, please try again.\n");
            } catch (IOException e) {
              throw new IllegalStateException("Unable to save image");
            }
            break;
          case "red-component":
            try {
              imageName = nextLine[1];
              newImageName = nextLine[2];
              cmd = new RedComponentGreyscaleCommand();
            } catch (ArrayIndexOutOfBoundsException e) {
              this.displayMessage("Invalid parameters specified, please try again.\n");
            }
            break;
          case "green-component":
            try {
              imageName = nextLine[1];
              newImageName = nextLine[2];
              cmd = new GreenComponentGreyscaleCommand();
            } catch (ArrayIndexOutOfBoundsException e) {
              this.displayMessage("Invalid parameters specified, please try again.\n");
            }
            break;
          case "blue-component":
            try {
              imageName = nextLine[1];
              newImageName = nextLine[2];
              cmd = new BlueComponentGreyscaleCommand();
            } catch (ArrayIndexOutOfBoundsException e) {
              this.displayMessage("Invalid parameters specified, please try again.\n");
            }
            break;
          case "value-component":
            try {
              imageName = nextLine[1];
              newImageName = nextLine[2];
              cmd = new ValueComponentGreyscaleCommand();
            } catch (ArrayIndexOutOfBoundsException e) {
              this.displayMessage("Invalid parameters specified, please try again.\n");
            }
            break;
          case "luma-component":
            try {
              imageName = nextLine[1];
              newImageName = nextLine[2];
              cmd = new LumaComponentGreyscaleCommand();
            } catch (ArrayIndexOutOfBoundsException e) {
              this.displayMessage("Invalid parameters specified, please try again.\n");
            }
            break;
          case "intensity-component":
            try {
              imageName = nextLine[1];
              newImageName = nextLine[2];
              cmd = new IntensityComponentGreyscaleCommand();
            } catch (ArrayIndexOutOfBoundsException e) {
              this.displayMessage("Invalid parameters specified, please try again.\n");
            }
            break;
          case "horizontal-flip":
            try {
              imageName = nextLine[1];
              newImageName = nextLine[2];
              cmd = new FlipHorizontalCommand();
            } catch (ArrayIndexOutOfBoundsException e) {
              this.displayMessage("Invalid parameters specified, please try again.\n");
            }
            break;
          case "vertical-flip":
            try {
              imageName = nextLine[1];
              newImageName = nextLine[2];
              cmd = new FlipVerticalCommand();
            } catch (ArrayIndexOutOfBoundsException e) {
              this.displayMessage("Invalid parameters specified, please try again.\n");
            }
            break;
          case "brighten":
            try {
              imageName = nextLine[1];
              newImageName = nextLine[2];
              cmd = new BrightenCommand(Integer.parseInt(nextLine[3]));
            } catch (ArrayIndexOutOfBoundsException|NumberFormatException e) {
              this.displayMessage("Invalid parameters specified, please try again.\n");
            }
            break;
        }
        if (cmd != null && imageName != null && newImageName != null) {
          try {
            this.process(imageName, newImageName, cmd);
          } catch (IllegalArgumentException e) {
            this.displayMessage("Invalid parameters specified, please try again.\n");
          }
        }
      }
    }
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

  /**
   * Displays the options.
   *
   * @throws IOException if the message cannot be displayed
   */
  private void displayOptions() throws IllegalStateException {
    this.displayMessage("Options:\n");
    this.displayMessage("load [image-path] [image-name]\n");
    this.displayMessage("save [image-path] [image-name]\n");
    this.displayMessage("red-component [image-name] [dest-image-name]\n");
    this.displayMessage("green-component [image-name] [dest-image-name]\n");
    this.displayMessage("blue-component [image-name] [dest-image-name]\n");
    this.displayMessage("value-component [image-name] [dest-image-name]\n");
    this.displayMessage("luma-component [image-name] [dest-image-name]\n");
    this.displayMessage("intensity-component [image-name] [dest-image-name]\n");
    this.displayMessage("horizontal-flip [image-name] [dest-image-name]\n");
    this.displayMessage("vertical-flip [image-name] [dest-image-name]\n");
    this.displayMessage("brighten [image-name] [dest-image-name] [increment]\n");
  }

  /**
   * Displays a message on the view.
   *
   * @param msg the message to display
   * @throws IllegalStateException if unable to display the message
   */
  private void displayMessage(String msg) throws IllegalStateException {
    try {
      this.view.renderMessage(msg);
    } catch (IOException e) {
      throw new IllegalStateException("Failed to display message");
    }
  }
}