package controller.text;

import controller.ImageProcessingController;
import controller.query.*;
import model.ExceptionMessage;
import model.ImageProcessingModel;
import view.text.ImageProcessingTextView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class represents the controller that handles interactions for the image
 * processing program.
 */
public class ImageProcessingTextControllerImpl implements ImageProcessingController {
  private final ImageProcessingModel model;
  private final ImageProcessingTextView view;
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
  public ImageProcessingTextControllerImpl(ImageProcessingModel model,
                                           ImageProcessingTextView view) {
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
  public ImageProcessingTextControllerImpl(ImageProcessingModel model, ImageProcessingTextView view,
                                           Readable readable) throws IllegalArgumentException {
    if (model == null || view == null || readable == null) {
      throw new IllegalArgumentException(ExceptionMessage.NULL_VALUES.toString());
    }
    this.model = model;
    this.view = view;
    this.readable = readable;
    this.quit = false;
    this.queries = new HashMap<String, QueryCommand>();
  }

  @Override
  public void start() throws IllegalStateException {
    this.queries.put("menu", new MenuQuery(model, view));
    this.queries.put("list", new ListQuery(model, view));
    this.queries.put("load", new LoadQuery(model, view));
    this.queries.put("save", new SaveQuery(model, view));
    this.queries.put("red-component", new RedComponentQuery(model, view));
    this.queries.put("green-component", new GreenComponentQuery(model, view));
    this.queries.put("blue-component", new BlueComponentQuery(model, view));
    this.queries.put("value-component", new ValueQuery(model, view));
    this.queries.put("luma-component", new LumaQuery(model, view));
    this.queries.put("intensity-component", new IntensityQuery(model, view));
    this.queries.put("horizontal-flip", new FlipHorizontalQuery(model, view));
    this.queries.put("vertical-flip", new FlipVerticalQuery(model, view));
    this.queries.put("brighten", new BrightenQuery(model, view));
    this.queries.put("greyscale", new LumaQuery(model, view));
    this.queries.put("gaussian-blur", new GaussianBlurQuery(model, view));
    this.queries.put("sharpen", new SharpenQuery(model, view));
    this.queries.put("sepia", new SepiaQuery(model, view));
    this.queries.put("mask", new ImageMaskQuery(model, view));
    this.queries.put("quit", new QuitQuery(model, view, () -> this.quit = true));
    this.queries.put("q", new QuitQuery(model, view, () -> this.quit = true));

    Scanner sc = new Scanner(this.readable);

    this.displayMessage("*** Image Processing Program ***\n");
    this.displayMessage("Enter a command to start.\n");

    while (!this.quit) {
      String[] query = sc.nextLine().split(" ");

      // Do nothing if query is empty.
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