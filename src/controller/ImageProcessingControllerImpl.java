package controller;

import controller.query.BlueComponentQuery;
import controller.query.BrightenQuery;
import controller.query.FlipHorizontalQuery;
import controller.query.FlipVerticalQuery;
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
import model.ExceptionMessage;
import model.ImageProcessingModel;
import view.ImageProcessingView;

import java.io.IOException;
import java.io.InputStreamReader;
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
   * view, and readable defaulted to {@code InputStreamReader}.
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
    this.quit = false;
    this.queries = new HashMap<String, QueryCommand>() {{
      put("menu", new MenuQuery(model, view));
      put("list", new ListQuery(model, view));
      put("load", new LoadQuery(model, view));
      put("save", new SaveQuery(model, view));
      put("red-component", new RedComponentQuery(model, view));
      put("green-component", new GreenComponentQuery(model, view));
      put("blue-component", new BlueComponentQuery(model, view));
      put("value-component", new ValueQuery(model, view));
      put("luma-component", new LumaQuery(model, view));
      put("intensity-component", new IntensityQuery(model, view));
      put("horizontal-flip", new FlipHorizontalQuery(model, view));
      put("vertical-flip", new FlipVerticalQuery(model, view));
      put("brighten", new BrightenQuery(model, view));
      put("gaussian-blur", new GaussianBlurQuery(model, view));
      put("sharpen", new SharpenQuery(model, view));
      put("sepia", new SepiaQuery(model, view));
    }};
    this.queries.put("quit", new QuitQuery(model, view, () -> this.quit = true));
    this.queries.put("q", new QuitQuery(model, view, () -> this.quit = true));
  }

  @Override
  public void start() throws IllegalStateException {
    Scanner sc = new Scanner(this.readable);

    this.displayMessage("*** Image Processing Program ***\n");
    this.displayMessage("Enter a command to start.\n");

    while (!this.quit) {
      String[] query = sc.nextLine().split(" ");

      if (query.length == 0) {
        continue;
      }

      String cmd = query[0].toLowerCase();
      QueryCommand queryCmd = this.queries.getOrDefault(cmd, null);

      if (queryCmd == null) {
        this.displayMessage(ExceptionMessage.INVALID_COMMAND_PARAMETERS.toString() + "\n");
        continue;
      }

      queryCmd.execute(query);
    }
    sc.close();
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