package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * This class tests the {@code Pixel} class and its methods.
 */
public class PixelTest {

  private Pixel black;
  private Pixel white;
  private Pixel yellow;

  @Before
  public void init() {
    this.black = new Pixel(1, 1, 1, 1);
    this.white = new Pixel(255, 0, 0, 0, 100);
    this.yellow = new Pixel(255, 252, 202, 3);
  }

  @Test
  public void testConstructor() {
    Pixel testPixel = new Pixel(255, 100, 200, 255);
    assertEquals(100, testPixel.getRed());
    assertEquals(200, testPixel.getGreen());
    assertEquals(255, testPixel.getBlue());
    assertEquals(255, testPixel.getAlpha());
    Pixel testPixelAlternate = new Pixel(255, 100, 200, 255, 100);
    assertEquals(100, testPixelAlternate.getRed());
    assertEquals(200, testPixelAlternate.getGreen());
    assertEquals(255, testPixelAlternate.getBlue());
    assertEquals(100, testPixelAlternate.getAlpha());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeMaxValue() {
    new Pixel(-255, 100, 200, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeRed() {
    new Pixel(255, -100, 200, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeGreen() {
    new Pixel(255, 100, -200, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeBlue() {
    new Pixel(255, 100, 200, -255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeAlpha() {
    new Pixel(255, 100, 200, 255, -255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorTooLargeRed() {
    new Pixel(255, 256, 200, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorTooLargeGreen() {
    new Pixel(255, 100, 256, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorTooLargeBlue() {
    new Pixel(255, 100, 200, 256);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorTooLargeAlpha() {
    new Pixel(255, 100, 200, 255, 256);
  }

  @Test
  public void testGetRed() {
    assertEquals(1, this.black.getRed());
    assertEquals(0, this.white.getRed());
    assertEquals(252, this.yellow.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(1, this.black.getGreen());
    assertEquals(0, this.white.getGreen());
    assertEquals(202, this.yellow.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(1, this.black.getBlue());
    assertEquals(0, this.white.getBlue());
    assertEquals(3, this.yellow.getBlue());
  }

  @Test
  public void testGetAlpha() {
    assertEquals(1, this.black.getAlpha());
    assertEquals(100, this.white.getAlpha());
    assertEquals(255, this.yellow.getAlpha());
  }

  @Test
  public void testGetMaxValue() {
    assertEquals(1, this.black.getMaxValue());
    assertEquals(255, this.white.getMaxValue());
    assertEquals(255, this.yellow.getMaxValue());
  }

  @Test
  public void testEquals() {
    Pixel pixelA = new Pixel(255, 1, 2, 3, 100);
    Pixel pixelB = new Pixel(255, 1, 2, 3, 100);
    Pixel pixelC = new Pixel(255, 1, 2, 255);
    Pixel pixelD = new Pixel(255, 1, 255, 3);
    Pixel pixelE = new Pixel(100, 1, 2, 3);
    assertEquals(pixelA, pixelB);
    assertNotEquals(pixelA, pixelC);
    assertNotEquals(pixelA, pixelD);
    assertNotEquals(pixelA, pixelE);
  }

  @Test
  public void testHashcode() {
    assertEquals(29583456, this.black.hashCode());
    assertEquals(28632506, this.white.hashCode());
    assertEquals(267385268, this.yellow.hashCode());
  }
}
