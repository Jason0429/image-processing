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
  public void testLoad() {
    ImageInterface test = ImageLoader.load("res/test3x4.ppm");
    assertEquals(3, test.getWidth());
    assertEquals(4, test.getHeight());
    assertEquals(255, test.getMaxValue());
    assertEquals(new Pixel(255, 252, 186, 3),
            test.getPixelAt(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadInvalidLocation() {
    ImageInterface test = ImageLoader.load("res/imagenotfound.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadNotP3() {
    ImageInterface test = ImageLoader.load("res/notP3.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadNotEnoughInts() {
    ImageInterface test = ImageLoader.load("res/notEnoughInts.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadWidthTooSmall() {
    ImageInterface test = ImageLoader.load("res/widthTooSmall.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadWidthTooLarge() {
    ImageInterface test = ImageLoader.load("res/widthTooLarge.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadHeightTooSmall() {
    ImageInterface test = ImageLoader.load("res/heightTooSmall.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadHeightTooLarge() {
    ImageInterface test = ImageLoader.load("res/heightTooLarge.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadTooManyInts() {
    ImageInterface test = ImageLoader.load("res/tooManyInts.ppm");
  }
}
