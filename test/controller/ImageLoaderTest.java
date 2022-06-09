package controller;

import org.junit.Test;

import model.Image;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests the {@code ImageLoader} class.
 */
public class ImageLoaderTest {

  @Test
  public void testLoad() {
    Image test = ImageLoader.load("test/test-images/test3x4.ppm");
    assertEquals(3, test.getWidth());
    assertEquals(4, test.getHeight());
    assertEquals(255, test.getMaxValue());
    assertEquals(new Pixel(255, 252, 186, 3),
            test.getPixelAt(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadInvalidLocation() {
    Image test = ImageLoader.load("test/test-images/imagenotfound.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadNotP3() {
    Image test = ImageLoader.load("test/test-images/notP3.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadNotEnoughInts() {
    Image test = ImageLoader.load("test/test-images/notEnoughInts.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadWidthTooSmall() {
    Image test = ImageLoader.load("test/test-images/widthTooSmall.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadWidthTooLarge() {
    Image test = ImageLoader.load("test/test-images/widthTooLarge.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadHeightTooSmall() {
    Image test = ImageLoader.load("test/test-images/heightTooSmall.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadHeightTooLarge() {
    Image test = ImageLoader.load("test/test-images/heightTooLarge.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadTooManyInts() {
    Image test = ImageLoader.load("test/test-images/tooManyInts.ppm");
  }
}
