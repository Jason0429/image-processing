import controller.ImageProcessingController;
import controller.gui.FeaturesImpl;
import controller.gui.ImageProcessingGUIControllerImpl;
import controller.text.ImageProcessingTextControllerImpl;
import model.ExceptionMessage;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.gui.ImageProcessingGUIView;
import view.gui.ImageProcessingGUIViewImpl;
import view.text.ImageProcessingTextTextViewImpl;
import view.text.ImageProcessingTextView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Scanner;

/**
 * This class represents an image processor program that can import, manipulate, and export .PPM
 * files.
 */
public class ImageProcessor {

  /**
   * Starts the program.
   *
   * @param args possible command line arguments including:
   *             [script-file-path] a file containing commands to run the program.
   * @throws IllegalArgumentException if invalid parameters are provided.
   */
  public static void main(String[] args) throws IllegalArgumentException {
    if (args.length == 0) {
      ImageProcessingGUIView view = new ImageProcessingGUIViewImpl();
      ImageProcessingController controller = new ImageProcessingGUIControllerImpl(
              new FeaturesImpl(view));
      controller.start();
    } else if (args.length == 1 && args[0].equals("-text")) {
      Readable in = new InputStreamReader(System.in);
      ImageProcessingModel model = new ImageProcessingModelImpl();
      ImageProcessingTextView view = new ImageProcessingTextTextViewImpl();
      ImageProcessingController controller = new ImageProcessingTextControllerImpl(model, view, in);
      controller.start();
    } else if (args.length == 2 && args[0].equals("-file")) {
      Readable in = ImageProcessor.loadScript(args[1]);
      ImageProcessingModel model = new ImageProcessingModelImpl();
      ImageProcessingTextView view = new ImageProcessingTextTextViewImpl();
      ImageProcessingController controller = new ImageProcessingTextControllerImpl(model, view, in);
      controller.start();
    } else {
      throw new IllegalArgumentException(ExceptionMessage.INVALID_COMMAND_PARAMETERS.toString());
    }
  }

  /**
   * Loads script file at specified file path into a {@code StringReader} by new line.
   *
   * @param filePath the file path to the script.
   * @return a {@code StringReader} containing each line of the script.
   * @throws RuntimeException if the specified file cannot be found.
   */
  private static StringReader loadScript(String filePath) throws RuntimeException {
    try {
      Scanner sc = new Scanner(new FileReader(filePath));
      StringBuilder sb = new StringBuilder();
      while (sc.hasNextLine()) {
        sb.append(sc.nextLine()).append("\n");
      }
      sb.append("q");
      return new StringReader(sb.toString());
    } catch (FileNotFoundException e) {
      throw new RuntimeException(ExceptionMessage.INVALID_FILE_PATH.toString());
    }
  }
}
