package controller;

import java.io.StringReader;

import model.ImageProcessingModel;
import view.ImageProcessingTextView;

import static org.junit.Assert.assertEquals;

public class IPCTester {

  /**
   * Creates a new input interaction.
   *
   * @param in the input text
   * @return the new interaction
   */
  static Interaction inputs(String in) {
    return (input, output) -> input.append(in).append('\n');
  }

  /**
   * Creates a new print interaction.
   *
   * @param lines the printed lines
   * @return the new interaction
   */
  static Interaction prints(String... lines) {
    return (input, output) -> {
      for (String line : lines) {
        output.append(line).append('\n');
      }
    };
  }

  /**
   * Performs a test run and compares the generated expected output to the actual
   * output of the
   * tested controller.
   *
   * @param model        the image processing model
   * @param interactions the list of interactions with the controller
   * @return true when completed
   */
  static boolean testRun(ImageProcessingModel model, Interaction... interactions) {
    StringBuilder fakeInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();
    for (Interaction interaction : interactions) {
      interaction.apply(fakeInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeInput.toString());
    StringBuilder actualOutput = new StringBuilder();
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            model, new ImageProcessingTextView(actualOutput), input);
    controller.start();
    assertEquals(expectedOutput.toString(), actualOutput.toString());
    return true;
  }
}
