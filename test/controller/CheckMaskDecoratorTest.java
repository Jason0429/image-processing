package controller;

import org.junit.Before;
import org.junit.Test;

import controller.query.CheckMaskDecorator;
import controller.query.QueryCommand;
import controller.query.SepiaQuery;
import model.ImageInterface;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.text.ImageProcessingTextViewImpl;
import view.text.ImageProcessingTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@code CheckMaskDecorator}.
 */
public class CheckMaskDecoratorTest {
  private ImageProcessingModel model;
  private ImageProcessingTextView view;
  private ImageInterface unprocessedImage;

  @Before
  public void init() {
    this.model = new ImageProcessingModelImpl();
    this.view = new ImageProcessingTextViewImpl();
  }

  @Test
  public void testExecute() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load res/mario.png mario"),
            IPCTester.prints("Successfully stored res/mario.png as: mario."),
            IPCTester.inputs("load res/mario-head-mask.png mario-mask"),
            IPCTester.prints("Successfully stored res/mario-head-mask.png as: mario-mask."),
            IPCTester.inputs("sepia mario mario-mask mario-head-mask"),
            IPCTester.prints("Successfully applied mask with filter sepia and stored as: "
                    + "mario-head-mask."),
            IPCTester.inputs("save res/mario-sepia-masked.png mario-head-mask"),
            IPCTester.prints("Successfully saved mario-head-mask to res/mario-sepia-masked.png."),
            IPCTester.inputs("q"),
            IPCTester.prints("Quitting Image Processing...")));
  }

  @Test
  public void testDoesNotSupportFlipping() {
    assertTrue(IPCTester.testRun(this.model,
            IPCTester.prints("*** Image Processing Program ***"),
            IPCTester.prints("Enter a command to start."),
            IPCTester.inputs("load res/mario.png mario"),
            IPCTester.prints("Successfully stored res/mario.png as: mario."),
            IPCTester.inputs("load res/mario-head-mask.png mario-mask"),
            IPCTester.prints("Successfully stored res/mario-head-mask.png as: mario-mask."),
            IPCTester.inputs("vertical-flip mario mario-mask mario-head-mask"),
            IPCTester.prints("Invalid parameters specified, please try again."),
            IPCTester.inputs("q"),
            IPCTester.prints("Quitting Image Processing...")));
  }

  @Test
  public void testProperQueryLength() {
    QueryCommand query = new SepiaQuery(this.model, this.view);
    QueryCommand checkMaskDecorator = new CheckMaskDecorator(query, this.model, this.view);
    assertEquals(checkMaskDecorator.getProperQueryLength(), query.getProperQueryLength() + 1);
  }
}
