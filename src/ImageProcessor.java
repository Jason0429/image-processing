import controller.ImageProcessingControllerImpl;
import model.ExceptionMessage;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingTextView;
import view.ImageProcessingView;

import java.io.*;
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
   */
  public static void main(String[] args) {
    Readable in = args.length == 1
            ? ImageProcessor.loadScript(args[0])
            : new InputStreamReader(System.in);

    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingTextView();
    ImageProcessingControllerImpl controller = new ImageProcessingControllerImpl(model, view, in);
    controller.start();
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
      return new StringReader(sb.toString());
    } catch (FileNotFoundException e) {
      throw new RuntimeException(ExceptionMessage.INVALID_FILE_PATH.toString());
    }
  }
}
