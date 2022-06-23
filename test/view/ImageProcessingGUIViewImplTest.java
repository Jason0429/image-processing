package view;

import controller.loader.ImageLoader;
import model.CommandType;
import org.junit.Before;
import org.junit.Test;
import controller.gui.Features;
import controller.gui.FeaturesImpl;

import static org.junit.Assert.assertEquals;

public class ImageProcessingGUIViewImplTest {
  private MockGUIView view;
  private Features features;
  private Appendable log;


  @Before
  public void init() {
    this.log = new StringBuilder();
    this.view = new MockGUIView(log);
    this.features = new FeaturesImpl(this.view);
  }

  // test apply with empty image
  @Test()
  public void testEmptyImageApply() {
    this.view.setCommandType(CommandType.RED_COMPONENT);
    this.features.apply();
    assertEquals("No image loaded.", this.log.toString());
  }

  // test apply with brighten with empty image
  @Test
  public void testEmptyImageApplyWithValue() {
    this.view.setCommandType(CommandType.BRIGHTEN);
    this.features.apply();
    assertEquals("No image loaded.", this.log.toString());
  }

  // test export with empty image
  @Test
  public void testExportEmptyImage() {
    this.view.setExportLocation("test.png");
    this.features.save();
    assertEquals("No image loaded.", this.log.toString());
  }

  // test load invalid file type
  @Test
  public void testLoadInvalidFileType() {
    this.view.setLoadLocation("res/script.txt");
    this.features.load();
    assertEquals("Unsupported file type.", this.log.toString());
  }

  // test load .ppm
  @Test
  public void testLoadPPM() {
    this.view.setLoadLocation("res/test3x4.ppm");
    this.features.load();
    assertEquals("", this.log.toString());
  }

  // test load .png
  @Test
  public void testLoadPNG() {
    this.view.setLoadLocation("res/test3x4.png");
    this.features.load();
    assertEquals("", this.log.toString());
  }

  // test load .bmp
  @Test
  public void testLoadBMP() {
    this.view.setLoadLocation("res/test3x4.bmp");
    this.features.load();
    assertEquals("", this.log.toString());
  }

  // test load .jpeg
  @Test
  public void testLoadJPEG() {
    this.view.setLoadLocation("res/test3x4.jpeg");
    this.features.load();
    assertEquals("", this.log.toString());
  }

  // test load .jpg
  @Test
  public void testLoadJPG() {
    this.view.setLoadLocation("res/test3x4.jpg");
    this.features.load();
    assertEquals("", this.log.toString());
  }

  // test apply blue, export, and compare to actual blue
  @Test
  public void testApplyBlue() {
    this.view.setCommandType(CommandType.BLUE_COMPONENT);
    this.view.setLoadLocation("res/test3x4.ppm");
    this.view.setExportLocation("res/test-blue-gui.ppm");
    this.features.load();
    this.features.apply();
    this.features.save();
    assertEquals(ImageLoader.load("res/test-blue.ppm"),
            ImageLoader.load("res/test-blue-gui.ppm"));
  }

  // test apply brighten, export, and compare to actual brighten
  @Test
  public void testApplyBrighten() {
    this.view.setCommandType(CommandType.BRIGHTEN);
    this.view.setLoadLocation("res/test3x4.ppm");
    this.view.setExportLocation("res/test-brighten-gui.ppm");
    this.features.load();
    this.features.apply();
    this.features.save();
    assertEquals(ImageLoader.load("res/test-brighten.ppm"),
            ImageLoader.load("res/test-brighten-gui.ppm"));
  }

  // test apply brighten darken, export, and compare to actual brighten darken
  // TODO: test
  @Test
  public void testApplyBrightenDark() {
//    this.features.update("res/test3x4.ppm");
//    this.features.processImage("brighten", -10);
//    this.features.exportImage("res/test-darken-gui.ppm");
//    assertEquals(ImageLoader.load("res/test-darken.ppm"),
//            ImageLoader.load("res/test-darken-gui.ppm"));
  }

  // test apply flip, export, and compare to actual flip
  @Test
  public void testApplyFlipHorizontal() {
    this.view.setCommandType(CommandType.HORIZONTAL_FLIP);
    this.view.setLoadLocation("res/test3x4.ppm");
    this.view.setExportLocation("res/test-horizontal-gui.ppm");
    this.features.load();
    this.features.apply();
    this.features.save();
    assertEquals(ImageLoader.load("res/test-horizontal.ppm"),
            ImageLoader.load("res/test-horizontal-gui.ppm"));
  }

  @Test
  public void testApplyFlipVertical() {
    this.view.setCommandType(CommandType.VERTICAL_FLIP);
    this.view.setLoadLocation("res/test3x4.ppm");
    this.view.setExportLocation("res/test-vertical-gui.ppm");
    this.features.load();
    this.features.apply();
    this.features.save();
    assertEquals(ImageLoader.load("res/test-vertical.ppm"),
            ImageLoader.load("res/test-vertical-gui.ppm"));
  }

  // test gaussian blur, export, and compare to actual gaussian blur
  @Test
  public void testApplyGaussian() {
    this.view.setCommandType(CommandType.GAUSSIAN_BLUR);
    this.view.setLoadLocation("res/test3x4.ppm");
    this.view.setExportLocation("res/test-gaussian-gui.ppm");
    this.features.load();
    this.features.apply();
    this.features.save();
    assertEquals(ImageLoader.load("res/test-gaussian.ppm"),
            ImageLoader.load("res/test-gaussian-gui.ppm"));
  }

  // test apply green, export, and compare to actual green
  @Test
  public void testApplyGreen() {
    this.view.setCommandType(CommandType.GREEN_COMPONENT);
    this.view.setLoadLocation("res/test3x4.ppm");
    this.view.setExportLocation("res/test-green-gui.ppm");
    this.features.load();
    this.features.apply();
    this.features.save();
    assertEquals(ImageLoader.load("res/test-green.ppm"),
            ImageLoader.load("res/test-green-gui.ppm"));
  }

  // test intensity, export, and compare to actual intensity
  @Test
  public void testApplyIntensity() {
    this.view.setCommandType(CommandType.INTENSITY_COMPONENT);
    this.view.setLoadLocation("res/test3x4.ppm");
    this.view.setExportLocation("res/test-intensity-gui.ppm");
    this.features.load();
    this.features.apply();
    this.features.save();
    assertEquals(ImageLoader.load("res/test-intensity.ppm"),
            ImageLoader.load("res/test-intensity-gui.ppm"));
  }

  // test luma, export, and compare to actual luma
  @Test
  public void testApplyLuma() {
    this.view.setCommandType(CommandType.LUMA_COMPONENT);
    this.view.setLoadLocation("res/test3x4.ppm");
    this.view.setExportLocation("res/test-luma-gui.ppm");
    this.features.load();
    this.features.apply();
    this.features.save();
    assertEquals(ImageLoader.load("res/test-luma.ppm"),
            ImageLoader.load("res/test-luma-gui.ppm"));
  }

  // test red, export, and compare to actual red
  @Test
  public void testApplyRed() {
    this.view.setCommandType(CommandType.RED_COMPONENT);
    this.view.setLoadLocation("res/test3x4.ppm");
    this.view.setExportLocation("res/test-red-gui.ppm");
    this.features.load();
    this.features.apply();
    this.features.save();
    assertEquals(ImageLoader.load("res/test-red.ppm"),
            ImageLoader.load("res/test-red-gui.ppm"));
  }

  // test sepia, export and compare to actual sepia
  @Test
  public void testApplySepia() {
    this.view.setCommandType(CommandType.SEPIA);
    this.view.setLoadLocation("res/test3x4.ppm");
    this.view.setExportLocation("res/test-sepia-gui.ppm");
    this.features.load();
    this.features.apply();
    this.features.save();
    assertEquals(ImageLoader.load("res/test-sepia.ppm"),
            ImageLoader.load("res/test-sepia-gui.ppm"));
  }

  // test sharpen, export, and compare to actual sharpen
  @Test
  public void testApplySharpen() {
    this.view.setCommandType(CommandType.SHARPEN);
    this.view.setLoadLocation("res/test3x4.ppm");
    this.view.setExportLocation("res/test-sharpen-gui.ppm");
    this.features.load();
    this.features.apply();
    this.features.save();
    assertEquals(ImageLoader.load("res/test-sharpen.ppm"),
            ImageLoader.load("res/test-sharpen-gui.ppm"));
  }

  // test value, export, and compare to actual value
  @Test
  public void testApplyValue() {
    this.view.setCommandType(CommandType.VALUE_COMPONENT);
    this.view.setLoadLocation("res/test3x4.ppm");
    this.view.setExportLocation("res/test-value-gui.ppm");
    this.features.load();
    this.features.apply();
    this.features.save();
    assertEquals(ImageLoader.load("res/test-value.ppm"),
            ImageLoader.load("res/test-value-gui.ppm"));
  }

  // test loading image, applying two commands, and then exporting
  @Test
  public void testMultiCommands() {
    this.view.setCommandType(CommandType.VALUE_COMPONENT);
    this.view.setLoadLocation("res/test3x4.ppm");
    this.view.setExportLocation("res/test-multi-gui.ppm");
    this.features.load();
    this.view.setCommandType(CommandType.SEPIA);
    this.features.apply();
    this.view.setCommandType(CommandType.GAUSSIAN_BLUR);
    this.features.apply();
    this.features.save();
    assertEquals(ImageLoader.load("res/test-multi.ppm"),
            ImageLoader.load("res/test-multi-gui.ppm"));
  }
}
