import model.Image;
import model.ImageLoader;
import model.Pixel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ImageTest {
  private Image image1;
  private Image image2;
  private Image image3;

  @Before
  public void init() {
    ImageLoader loader = new ImageLoader();
    this.image1 = loader.getImageFromPPM("images/test3x4.ppm");
  }

  @Test
  public void testConstructor() {
    ImageLoader loader = new ImageLoader();
    Image test = loader.getImageFromPPM("images/test3x4.ppm");
    Pixel topLeft = new Pixel(255, 252, 186, 3);
    assertEquals(3, test.getWidth());
    assertEquals(4, test.getHeight());
    assertEquals(topLeft, test.getPixelAt(0, 0));
    assertEquals(255, test.getMaxValue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorZeroMaxValue() {
    new Image(new Pixel[3][3], 0, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorZeroWidth() {
    new Image(new Pixel[3][3], 1, 0, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorZeroHeight() {
    new Image(new Pixel[3][3], 1, 3, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorMismatchedDimensionsWidth() {
    new Image(new Pixel[3][3], 1, 4, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorMismatchedDimensionsHeight() {
    new Image(new Pixel[3][3], 1, 2, 4);
  }

  @Test
  public void testGetWidth() {
    assertEquals(3, this.image1.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(4, this.image1.getHeight());
  }

  @Test
  public void testGetMaxValue() {
    assertEquals(255, this.image1.getMaxValue());
  }

  @Test
  public void testGetPixelAt() {
    assertEquals(new Pixel(255, 252, 186, 3),
            this.image1.getPixelAt(0, 0));
  }

  @Test
  public void testGetPixelAtNegativeRow() {
    this.image1.getPixelAt(-1, 0);
  }

  @Test
  public void testGetPixelAtTooLargeRow() {
    this.image1.getPixelAt(999, 0);
  }

  @Test
  public void testGetPixelAtNegativeCol() {
    this.image1.getPixelAt(0, -1);
  }

  @Test
  public void testGetPixelAtTooLargeCol() {
    this.image1.getPixelAt(0, 999);
  }

  @Test
  public void testToPPM() {
    String image1PPM = "P3\n" +
            "3 4\n" +
            "255\n" +
            "252\n" +
            "186\n" +
            "3\n" +
            "252\n" +
            "227\n" +
            "3\n" +
            "161\n" +
            "252\n" +
            "3\n" +
            "252\n" +
            "186\n" +
            "3\n" +
            "252\n" +
            "227\n" +
            "3\n" +
            "161\n" +
            "252\n" +
            "3\n" +
            "252\n" +
            "186\n" +
            "3\n" +
            "252\n" +
            "227\n" +
            "3\n" +
            "161\n" +
            "252\n" +
            "3\n" +
            "252\n" +
            "186\n" +
            "3\n" +
            "252\n" +
            "227\n" +
            "3\n" +
            "161\n" +
            "252\n" +
            "3\n";
    assertEquals(image1PPM, this.image1.toPPMString());
  }

  @Test
  public void testCopy() {
    assertTrue(this.image1.copy().equals(this.image1));
  }
}
