package controller;

import controller.query.BlueComponentQuery;
import controller.query.BrightenQuery;
import controller.query.GaussianBlurQuery;
import controller.query.GreenComponentQuery;
import controller.query.IntensityQuery;
import controller.query.ListQuery;
import controller.query.LoadQuery;
import controller.query.LumaQuery;
import controller.query.MenuQuery;
import controller.query.QueryCommand;
import controller.query.QuitQuery;
import controller.query.RedComponentQuery;
import controller.query.SaveQuery;
import controller.query.SepiaQuery;
import controller.query.SharpenQuery;
import controller.query.ValueQuery;
import controller.query.VerticalFlipQuery;
import model.ExceptionMessage;
import model.ImageProcessingModel;
import view.ImageProcessingView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class represents the controller that handles interactions for the image
 * processing program.
 */
public class ImageProcessingControllerImpl implements ImageProcessingController {
  private final ImageProcessingModel model;
  private final ImageProcessingView view;
  private final Readable readable;
  private final Map<String, QueryCommand> queries;
  private boolean quit;

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
   * view, and readable.
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
    this.queries = new HashMap<String, QueryCommand>();
    queries.put("menu", new MenuQuery(this::displayMenu));
    queries.put("list", new ListQuery(this.model, this::displayMessage));
    queries.put("load", new LoadQuery(this.model, this::displayInvalidCommandParametersError,
            this::displayMessage));
    queries.put("save", new SaveQuery(this.model, this::displayInvalidCommandParametersError,
            this::displayMessage));
    queries.put("red-component", new RedComponentQuery(this.model,
            this::displayInvalidCommandParametersError, this::displayMessage));
    queries.put("green-component", new GreenComponentQuery(this.model,
            this::displayInvalidCommandParametersError, this::displayMessage));
    queries.put("blue-component", new BlueComponentQuery(this.model,
            this::displayInvalidCommandParametersError, this::displayMessage));
    queries.put("value-component", new ValueQuery(this.model,
            this::displayInvalidCommandParametersError, this::displayMessage));
    queries.put("luma-component", new LumaQuery(this.model,
            this::displayInvalidCommandParametersError, this::displayMessage));
    queries.put("intensity-component", new IntensityQuery(this.model,
            this::displayInvalidCommandParametersError, this::displayMessage));
    queries.put("horizontal-flip", new HorizontalFlipQuery(this.model,
            this::displayInvalidCommandParametersError, this::displayMessage));
    queries.put("vertical-flip", new VerticalFlipQuery(this.model,
            this::displayInvalidCommandParametersError, this::displayMessage));
    queries.put("brighten", new BrightenQuery(this.model,
            this::displayInvalidCommandParametersError, this::displayMessage));
    queries.put("gaussian-blur", new GaussianBlurQuery(this.model,
            this::displayInvalidCommandParametersError, this::displayMessage));
    queries.put("sepia", new SepiaQuery(this.model, this::displayInvalidCommandParametersError,
            this::displayMessage));
    queries.put("sharpen", new SharpenQuery(this.model,
            this::displayInvalidCommandParametersError, this::displayMessage));
    queries.put("quit", new QuitQuery(() -> this.quit = true));
    queries.put("q", new QuitQuery(() -> this.quit = true));
    this.quit = false;
  }

  @Override
  public void start() throws IllegalStateException {
    Scanner sc = new Scanner(this.readable);

    this.displayMessage("*** Image Processing Program ***\nEnter a command to start.\n");
    while (!this.quit) {
      String[] query = sc.nextLine().split(" ");

      if (query.length == 0) {
        continue;
      }

      String cmdType = query[0].toLowerCase();

      QueryCommand cmd = this.queries.getOrDefault(cmdType, null);
      if (cmd != null) {
        cmd.execute(query.length > 1 ? Arrays.copyOfRange(query, 1, query.length) : new String[0]);
      } else {
        this.displayMessage(ExceptionMessage.INVALID_COMMAND_PARAMETERS.toString() + "\n");
      }
    }
    sc.close();
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
    this.displayMessage("menu (loads this menu)\n");
    this.displayMessage("list (lists all loaded images)\n");
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
    this.displayMessage("quit/q (quit the program)\n");
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