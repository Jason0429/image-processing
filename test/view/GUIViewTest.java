package view;

import controller.loader.ImageLoader;
import org.junit.Before;
import org.junit.Test;
import controller.gui.Features;
import controller.gui.FeaturesImpl;
import controller.gui.GUIController;
import view.gui.GUIView;

import static org.junit.Assert.assertEquals;

public class GUIViewTest {
  private GUIView view;
  private Features features;
  private GUIController controller;

  @Before
  public void init() {
    this.view = new GUIView();
    this.features = new FeaturesImpl(this.view, null);
    this.controller = new GUIController(null, view, this.features);
  }

  // test apply with empty image
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyImageApply() {
    this.features.processImage("red-component");
  }

  // test apply with brighten with empty image
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyImageApplyWithValue() {
    this.features.processImage("brighten", 10);
  }

  // test export with empty image
  @Test(expected = IllegalArgumentException.class)
  public void testExportEmptyImage() {
    this.features.exportImage("test.png");
  }

  // test load invalid file type
  @Test(expected = IllegalArgumentException.class)
  public void testLoadInvalidFileType() {
    this.features.update("res/script.txt");
  }

  // test load .ppm
  @Test
  public void testLoadPPM() {
    this.features.update("res/test3x4.ppm");
  }

  // test load .png
  @Test
  public void testLoadPNG() {
    this.features.update("res/test3x4.png");
  }

  // test load .bmp
  @Test
  public void testLoadBMP() {
    this.features.update("res/test3x4.bmp");
  }

  // test load .jpeg
  @Test
  public void testLoadJPEG() {
    this.features.update("res/test3x4.jpeg");
  }

  // test load .jpg
  @Test
  public void testLoadJPG() {
    this.features.update("res/test3x4.jpg");
  }

  // test apply blue, export, and compare to actual blue
  @Test
  public void testApplyBlue() {
    this.features.update("res/test3x4.ppm");
    this.features.processImage("blue-component");
    this.features.exportImage("res/test-blue-gui.ppm");
    assertEquals(ImageLoader.load("res/test-blue.ppm"),
            ImageLoader.load("res/test-blue-gui.ppm"));
  }

  // test apply brighten, export, and compare to actual brighten
  @Test
  public void testApplyBrighten() {
    this.features.update("res/test3x4.ppm");
    this.features.processImage("brighten", 10);
    this.features.exportImage("res/test-brighten-gui.ppm");
    assertEquals(ImageLoader.load("res/test-brighten.ppm"),
            ImageLoader.load("res/test-brighten-gui.ppm"));
  }

  // test apply brighten darken, export, and compare to actual brighten darken
  @Test
  public void testApplyBrightenDark() {
    this.features.update("res/test3x4.ppm");
    this.features.processImage("brighten", -10);
    this.features.exportImage("res/test-darken-gui.ppm");
    assertEquals(ImageLoader.load("res/test-darken.ppm"),
            ImageLoader.load("res/test-darken-gui.ppm"));
  }

  // test apply flip, export, and compare to actual flip
  @Test
  public void testApplyFlipHorizontal() {
    this.features.update("res/test3x4.ppm");
    this.features.processImage("horizontal-flip");
    this.features.exportImage("res/test-horizontal-gui.ppm");
    assertEquals(ImageLoader.load("res/test-horizontal.ppm"),
            ImageLoader.load("res/test-horizontal-gui.ppm"));
  }

  @Test
  public void testApplyFlipVertical() {
    this.features.update("res/test3x4.ppm");
    this.features.processImage("vertical-flip");
    this.features.exportImage("res/test-vertical-gui.ppm");
    assertEquals(ImageLoader.load("res/test-vertical.ppm"),
            ImageLoader.load("res/test-vertical-gui.ppm"));
  }

  // test gaussian blur, export, and compare to actual gaussian blur
  @Test
  public void testApplyGaussian() {
    this.features.update("res/test3x4.ppm");
    this.features.processImage("gaussian-blur");
    this.features.exportImage("res/test-gaussian-gui.ppm");
    assertEquals(ImageLoader.load("res/test-gaussian.ppm"),
            ImageLoader.load("res/test-gaussian-gui.ppm"));
  }

  // test apply green, export, and compare to actual green
  @Test
  public void testApplyGreen() {
    this.features.update("res/test3x4.ppm");
    this.features.processImage("green-component");
    this.features.exportImage("res/test-green-gui.ppm");
    assertEquals(ImageLoader.load("res/test-green.ppm"),
            ImageLoader.load("res/test-green-gui.ppm"));
  }

  // test intensity, export, and compare to actual intensity
  @Test
  public void testApplyIntensity() {
    this.features.update("res/test3x4.ppm");
    this.features.processImage("intensity-component");
    this.features.exportImage("res/test-intensity-gui.ppm");
    assertEquals(ImageLoader.load("res/test-intensity.ppm"),
            ImageLoader.load("res/test-intensity-gui.ppm"));
  }

  // test luma, export, and compare to actual luma
  @Test
  public void testApplyLuma() {
    this.features.update("res/test3x4.ppm");
    this.features.processImage("luma-component");
    this.features.exportImage("res/test-luma-gui.ppm");
    assertEquals(ImageLoader.load("res/test-luma.ppm"),
            ImageLoader.load("res/test-luma-gui.ppm"));
  }

  // test red, export, and compare to actual red
  @Test
  public void testApplyRed() {
    this.features.update("res/test3x4.ppm");
    this.features.processImage("red-component");
    this.features.exportImage("res/test-red-gui.ppm");
    assertEquals(ImageLoader.load("res/test-red.ppm"),
            ImageLoader.load("res/test-red-gui.ppm"));
  }

  // test sepia, export and compare to actual sepia
  @Test
  public void testApplySepia() {
    this.features.update("res/test3x4.ppm");
    this.features.processImage("sepia");
    this.features.exportImage("res/test-sepia-gui.ppm");
    assertEquals(ImageLoader.load("res/test-sepia.ppm"),
            ImageLoader.load("res/test-sepia-gui.ppm"));
  }

  // test sharpen, export, and compare to actual sharpen
  @Test
  public void testApplySharpen() {
    this.features.update("res/test3x4.ppm");
    this.features.processImage("sharpen");
    this.features.exportImage("res/test-sharpen-gui.ppm");
    assertEquals(ImageLoader.load("res/test-sharpen.ppm"),
            ImageLoader.load("res/test-sharpen-gui.ppm"));
  }

  // test value, export, and compare to actual value
  @Test
  public void testApplyValue() {
    this.features.update("res/test3x4.ppm");
    this.features.processImage("value-component");
    this.features.exportImage("res/test-value-gui.ppm");
    assertEquals(ImageLoader.load("res/test-value.ppm"),
            ImageLoader.load("res/test-value-gui.ppm"));
  }

  // test loading image, applying two commands, and then exporting
  @Test
  public void testMultiCommands() {
    this.features.update("res/test3x4.ppm");
    this.features.processImage("sepia");
    this.features.processImage("gaussian-blur");
  }
}
