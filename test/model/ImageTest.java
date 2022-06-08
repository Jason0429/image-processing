package model;

import model.Image;
import controller.ImageLoader;

import model.Pixel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ImageTest {
  private Image image1;
  private Image image2;

  @Before
  public void init() {
    this.image1 = ImageLoader.load("images/test3x4.ppm");
    this.image2 = ImageLoader.load("images/test3x3.ppm");
  }

  @Test
  public void testConstructor() {
    Image test = ImageLoader.load("images/test3x4.ppm");
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

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAtNegativeRow() {
    this.image1.getPixelAt(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAtTooLargeRow() {
    this.image1.getPixelAt(999, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAtNegativeCol() {
    this.image1.getPixelAt(0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
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

  @Test
  public void testEquals() {
    Pixel[][] pixelMatrix = new Pixel[4][3];
    pixelMatrix[0][0] = new Pixel(255, 252, 186, 3);
    pixelMatrix[1][0] = new Pixel(255, 252, 186, 3);
    pixelMatrix[2][0] = new Pixel(255, 252, 186, 3);
    pixelMatrix[3][0] = new Pixel(255, 252, 186, 3);
    pixelMatrix[0][1] = new Pixel(255, 252, 227, 3);
    pixelMatrix[1][1] = new Pixel(255, 252, 227, 3);
    pixelMatrix[2][1] = new Pixel(255, 252, 227, 3);
    pixelMatrix[3][1] = new Pixel(255, 252, 227, 3);
    pixelMatrix[0][2] = new Pixel(255, 161, 252, 3);
    pixelMatrix[1][2] = new Pixel(255, 161, 252, 3);
    pixelMatrix[2][2] = new Pixel(255, 161, 252, 3);
    pixelMatrix[3][2] = new Pixel(255, 161, 252, 3);
    Image copy = new Image(pixelMatrix, 255, 3, 4);
    assertEquals(this.image1, copy);
    assertNotEquals(this.image1, this.image2);
  }

  @Test
  public void testHashCode() {
    assertEquals(-1080619362, this.image1.hashCode());
    assertEquals(-218450216, this.image2.hashCode());
  }
}
