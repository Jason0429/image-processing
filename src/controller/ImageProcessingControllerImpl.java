package controller;

import model.commands.BlueComponentGreyscaleCommand;
import model.commands.BrightenCommand;
import model.commands.FlipHorizontalCommand;
import model.commands.FlipVerticalCommand;
import model.commands.GreenComponentGreyscaleCommand;
import model.commands.IntensityComponentGreyscaleCommand;
import model.commands.LumaComponentGreyscaleCommand;
import model.commands.RedComponentGreyscaleCommand;
import model.commands.ValueComponentGreyscaleCommand;
import model.ExceptionMessage;
import model.Image;
import model.ImageProcessingModel;
import view.ImageProcessingView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * This class represents the controller that handles interactions for the image
 * processing program.
 */
public class ImageProcessingControllerImpl implements ImageProcessingController {
  private final ImageProcessingModel model;
  private final ImageProcessingView view;
  private final Readable readable;

  /**
   * Constructs a {@code ImageProcessingControllerImpl} with a specified model,
   * view,
   * and readable defaulted to {@code InputStreamReader}.
   *
   * @param model the model to be used in the controller.
   * @param view  the view to be used in the controller.
   */
  public ImageProcessingControllerImpl(ImageProcessingModel model, ImageProcessingView view) {
    this(model, view, new InputStreamReader(System.in));
  }

  /**
   * Constructs a {@code ImageProcessingControllerImpl} with a specified model,
   * view,
   * and readable.
   *
   * @param model    the model to be used in the controller.
   * @param view     the view to be used in the controller.
   * @param readable the readable that the controller will use to take in input.
   * @throws IllegalArgumentException if any argument is null.
   */
  public ImageProcessingControllerImpl(ImageProcessingModel model, ImageProcessingView view,
      Readable readable) throws IllegalArgumentException {
    if (model == null || view == null || readable == null) {
      throw new IllegalArgumentException(ExceptionMessage.NULL_VALUES.toString());
    }
    this.model = model;
    this.view = view;
    this.readable = readable;
  }

  @Override
  public void start() throws IllegalStateException {
    Scanner sc = new Scanner(this.readable);
    boolean quit = false;

    while (!quit) {
      String[] query = sc.nextLine().split(" ");

      if (query.length == 0) {
        continue;
      }

      String cmdType = query[0];
      switch (cmdType) {
        case "menu":
          this.displayMenu();
          break;
        case "list":
          this.handleList();
          break;
        case "load":
          this.handleLoad(query);
          break;
        case "save":
          this.handleSave(query);
          break;
        case "red-component":
          this.handleRedComponent(query);
          break;
        case "green-component":
          this.handleGreenComponent(query);
          break;
        case "blue-component":
          this.handleBlueComponent(query);
          break;
        case "value-component":
          this.handleValueComponent(query);
          break;
        case "luma-component":
          this.handleLumaComponent(query);
          break;
        case "intensity-component":
          this.handleIntensityComponent(query);
          break;
        case "horizontal-flip":
          this.handleHorizontalFlip(query);
          break;
        case "vertical-flip":
          this.handleVerticalFlip(query);
          break;
        case "brighten":
          this.handleBrighten(query);
          break;
        case "quit":
        case "q":
          quit = true;
          break;
        default:
          this.displayMessage(ExceptionMessage.INVALID_COMMAND_PARAMETERS.toString() + "\n");
          break;
      }
    }
  }

  /**
   * Handles creating and storing a new brightened or darkened (or neither) image.
   *
   * @param query the command line query.
   */
  private void handleBrighten(String[] query) {
    if (query.length != 4) {
      this.displayInvalidCommandParametersError();
      return;
    }

    try {
      int change = Integer.parseInt(query[1]);
      String imageName = query[2];
      String destImageName = query[3];
      Image unprocessedImage = this.model.getImage(imageName);
      Image processedImage = new BrightenCommand(change).process(unprocessedImage);
      this.model.storeImage(destImageName, processedImage);
      this.displayMessage(
          "Successfully brightened image and stored as: " + destImageName + "\n");
    } catch (IllegalArgumentException e) {
      this.displayInvalidCommandParametersError();
    }
  }

  /**
   * Handles creating and storing a new image flipped vertically.
   *
   * @param query the command line query.
   */
  private void handleVerticalFlip(String[] query) {
    if (query.length != 3) {
      this.displayInvalidCommandParametersError();
      return;
    }

    try {
      String imageName = query[1];
      String destImageName = query[2];
      Image unprocessedImage = this.model.getImage(imageName);
      Image processedImage = new FlipVerticalCommand().process(unprocessedImage);
      this.model.storeImage(destImageName, processedImage);
      this.displayMessage(
          "Successfully flipped image vertically and stored as: " + destImageName + "\n");
    } catch (IllegalArgumentException e) {
      this.displayInvalidCommandParametersError();
    }
  }

  /**
   * Handles creating and storing a new image flipped horizontally.
   *
   * @param query the command line query.
   */
  private void handleHorizontalFlip(String[] query) {
    if (query.length != 3) {
      this.displayInvalidCommandParametersError();
      return;
    }

    try {
      String imageName = query[1];
      String destImageName = query[2];
      Image unprocessedImage = this.model.getImage(imageName);
      Image processedImage = new FlipHorizontalCommand().process(unprocessedImage);
      this.model.storeImage(destImageName, processedImage);
      this.displayMessage(
          "Successfully flipped image horizontally and stored as: " + destImageName + "\n");
    } catch (IllegalArgumentException e) {
      this.displayInvalidCommandParametersError();
    }
  }

  /**
   * Handles creating and storing a new image with intensity component applied.
   *
   * @param query the command line query.
   */
  private void handleIntensityComponent(String[] query) {
    if (query.length != 3) {
      this.displayInvalidCommandParametersError();
      return;
    }

    try {
      String imageName = query[1];
      String destImageName = query[2];
      Image unprocessedImage = this.model.getImage(imageName);
      Image processedImage = new IntensityComponentGreyscaleCommand().process(unprocessedImage);
      this.model.storeImage(destImageName, processedImage);
      this.displayMessage(
          "Successfully applied intensity component and stored as: " + destImageName + "\n");
    } catch (IllegalArgumentException e) {
      this.displayInvalidCommandParametersError();
    }
  }

  /**
   * Handles creating and storing a new image with luma component applied.
   *
   * @param query the command line query.
   */
  private void handleLumaComponent(String[] query) {
    if (query.length != 3) {
      this.displayInvalidCommandParametersError();
      return;
    }

    try {
      String imageName = query[1];
      String destImageName = query[2];
      Image unprocessedImage = this.model.getImage(imageName);
      Image processedImage = new LumaComponentGreyscaleCommand().process(unprocessedImage);
      this.model.storeImage(destImageName, processedImage);
      this.displayMessage(
          "Successfully applied luma component and stored as: " + destImageName + "\n");
    } catch (IllegalArgumentException e) {
      this.displayInvalidCommandParametersError();
    }
  }

  /**
   * Handles creating and storing a new image with value component applied.
   *
   * @param query the command line query.
   */
  private void handleValueComponent(String[] query) {
    if (query.length != 3) {
      this.displayInvalidCommandParametersError();
      return;
    }

    try {
      String imageName = query[1];
      String destImageName = query[2];
      Image unprocessedImage = this.model.getImage(imageName);
      Image processedImage = new ValueComponentGreyscaleCommand().process(unprocessedImage);
      this.model.storeImage(destImageName, processedImage);
      this.displayMessage(
          "Successfully applied value component and stored as: " + destImageName + "\n");
    } catch (IllegalArgumentException e) {
      this.displayInvalidCommandParametersError();
    }
  }

  /**
   * Handles creating and storing a new image with blue greyscale component
   * applied.
   *
   * @param query the command line query.
   */
  private void handleBlueComponent(String[] query) {
    if (query.length != 3) {
      this.displayInvalidCommandParametersError();
      return;
    }

    try {
      String imageName = query[1];
      String destImageName = query[2];
      Image unprocessedImage = this.model.getImage(imageName);
      Image processedImage = new BlueComponentGreyscaleCommand().process(unprocessedImage);
      this.model.storeImage(destImageName, processedImage);
      this.displayMessage(
          "Successfully applied blue component and stored as: " + destImageName + "\n");
    } catch (IllegalArgumentException e) {
      this.displayInvalidCommandParametersError();
    }
  }

  /**
   * Handles creating and storing a new image with green greyscale component
   * applied.
   *
   * @param query the command line query.
   */
  private void handleGreenComponent(String[] query) {
    if (query.length != 3) {
      this.displayInvalidCommandParametersError();
      return;
    }

    try {
      String imageName = query[1];
      String destImageName = query[2];
      Image unprocessedImage = this.model.getImage(imageName);
      Image processedImage = new GreenComponentGreyscaleCommand().process(unprocessedImage);
      this.model.storeImage(destImageName, processedImage);
      this.displayMessage(
          "Successfully applied green component and stored as: " + destImageName + "\n");
    } catch (IllegalArgumentException e) {
      this.displayInvalidCommandParametersError();
    }
  }

  /**
   * Handles creating and storing a new image with red greyscale component
   * applied.
   *
   * @param query the command line query.
   */
  private void handleRedComponent(String[] query) {
    if (query.length != 3) {
      this.displayInvalidCommandParametersError();
      return;
    }

    try {
      String imageName = query[1];
      String destImageName = query[2];
      Image unprocessedImage = this.model.getImage(imageName);
      Image processedImage = new RedComponentGreyscaleCommand().process(unprocessedImage);
      this.model.storeImage(destImageName, processedImage);
      this.displayMessage(
          "Successfully applied red component and stored as: " + destImageName + "\n");
    } catch (IllegalArgumentException e) {
      this.displayInvalidCommandParametersError();
    }
  }

  /**
   * Handles saving the specified image to a specific file path.
   *
   * @param query the command line query.
   */
  private void handleSave(String[] query) {
    if (query.length != 3) {
      this.displayInvalidCommandParametersError();
      return;
    }

    try {
      String filePath = query[1];
      String imageName = query[2];
      Image image = this.model.getImage(imageName);
      ImageExporter.export(image, filePath);
      this.displayMessage("Successfully saved " + imageName + " at " + filePath + "\n");
    } catch (IOException e) {
      this.displayInvalidCommandParametersError();
    }
  }

  /**
   * Handles listing the currently stored images names.
   */
  private void handleList() {
    String[] imageNames = this.model.getImageNames();
    if (imageNames.length == 0) {
      this.displayMessage("There are no images stored at the moment.li\n");
      return;
    }

    for (String imageName : this.model.getImageNames()) {
      this.displayMessage(imageName + "\n");
    }
  }

  /**
   * Handles loading the image into the model's image storage.
   *
   * @param query the command line query.
   */
  private void handleLoad(String[] query) {
    if (query.length != 3) {
      this.displayInvalidCommandParametersError();
      return;
    }

    try {
      String imagePath = query[1];
      String imageName = query[2];
      Image image = ImageLoader.load(imagePath);
      this.model.storeImage(imageName, image);
      this.displayMessage("Successfully loaded " + imageName + " from " + imagePath + "\n");
    } catch (IllegalArgumentException e) {
      this.displayInvalidCommandParametersError();
    }
  }

  /**
   * Provides feedback to user informing them they have entered
   * an invalid command or parameters.
   */
  private void displayInvalidCommandParametersError() {
    this.displayMessage(ExceptionMessage.INVALID_COMMAND_PARAMETERS.toString() + "\n");
  }

  /**
   * Displays the options.
   *
   * @throws IllegalStateException if the message cannot be displayed
   */
  private void displayMenu() throws IllegalStateException {
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
    this.displayMessage("brighten [increment] [image-name] [dest-image-name]\n");
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