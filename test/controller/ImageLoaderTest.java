package controller;

import controller.loader.ImageLoader;
import model.ImageInterface;

import org.junit.Test;

import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests the {@code ImageLoader} class.
 */
public class ImageLoaderTest {

  @Test
  public void testLoadPPM() {
    ImageInterface test = ImageLoader.load("res/test3x4.ppm");
    assertEquals(3, test.getWidth());
    assertEquals(4, test.getHeight());
    assertEquals(255, test.getMaxValue());
    assertEquals(new Pixel(255, 252, 186, 3),
            test.getPixelAt(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadInvalidLocationPPM() {
    ImageInterface test = ImageLoader.load("res/imagenotfound.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadNotP3PPM() {
    ImageInterface test = ImageLoader.load("res/notP3.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadNotEnoughIntsPPM() {
    ImageInterface test = ImageLoader.load("res/notEnoughInts.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadWidthTooSmallPPM() {
    ImageInterface test = ImageLoader.load("res/widthTooSmall.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadWidthTooLargePPM() {
    ImageInterface test = ImageLoader.load("res/widthTooLarge.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadHeightTooSmallPPM() {
    ImageInterface test = ImageLoader.load("res/heightTooSmall.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadHeightTooLargePPM() {
    ImageInterface test = ImageLoader.load("res/heightTooLarge.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadTooManyIntsPPM() {
    ImageInterface test = ImageLoader.load("res/tooManyInts.ppm");
  }

  @Test
  public void testLoadBMP() {
    ImageInterface test = ImageLoader.load("res/test3x4.bmp");
    assertEquals(3, test.getWidth());
    assertEquals(4, test.getHeight());
    assertEquals(255, test.getMaxValue());
    assertEquals(new Pixel(255, 252, 186, 3),
            test.getPixelAt(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadCorruptBMP() {
    ImageLoader.load("res/test3x4-corrupted.bmp");
  }

  @Test
  public void testLoadPNG() {
    ImageInterface test = ImageLoader.load("res/test3x4.png");
    assertEquals(3, test.getWidth());
    assertEquals(4, test.getHeight());
    assertEquals(255, test.getMaxValue());
    assertEquals(new Pixel(255, 252, 186, 3),
            test.getPixelAt(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadCorruptPNG() {
    ImageLoader.load("res/test3x4-corrupted.png");
  }

  @Test
  public void testLoadJPEG() {
    ImageInterface test = ImageLoader.load("res/test3x4.jpeg");
    assertEquals(3, test.getWidth());
    assertEquals(4, test.getHeight());
    assertEquals(255, test.getMaxValue());
    assertEquals(new Pixel(255, 223, 203, 0), test.getPixelAt(0, 0));
  }

  @Test
  public void testLoadJPG() {
    ImageInterface test = ImageLoader.load("res/test3x4.jpg");
    assertEquals(3, test.getWidth());
    assertEquals(4, test.getHeight());
    assertEquals(255, test.getMaxValue());
    assertEquals(new Pixel(255, 223, 203, 0), test.getPixelAt(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadCorruptJPEG() {
    ImageLoader.load("res/test3x4-corrupted.jpeg");
  }
}
