import controller.ImageProcessingControllerImpl;
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
   * @param args takes no arguments
   */
  public static void main(String[] args) {
    Readable in;
    if (args.length == 1) {
      String filePath = args[0];
      try {
        Scanner scn = new Scanner(new FileReader(filePath));
        StringBuilder sb = new StringBuilder();
        while (scn.hasNextLine()) {
          sb.append(scn.nextLine()).append("\n");
        }
        in = new StringReader(sb.toString());
      } catch (FileNotFoundException e) {
        throw new RuntimeException("Invalid file path specified.");
      }
    } else {
      in = new InputStreamReader(System.in);
    }

    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingView view = new ImageProcessingTextView();
    ImageProcessingControllerImpl controller = new ImageProcessingControllerImpl(model, view, in);
    controller.start();
  }
}
