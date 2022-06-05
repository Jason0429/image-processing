import model.Pixel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the Pixel class and its methods.
 */
public class PixelTest {

  private Pixel black;
  private Pixel white;
  private Pixel yellow;

  @Before
  public void init() {
    this.black = new Pixel(1, 1, 1, 1);
    this.white = new Pixel(255, 0, 0, 0);
    this.yellow = new Pixel(255, 252, 202, 3);
  }

  @Test
  public void testConstructor() {
    Pixel testPixel = new Pixel(255, 100, 200, 255);
    assertEquals(100, testPixel.getRed());
    assertEquals(200, testPixel.getGreen());
    assertEquals(255, testPixel.getBlue());
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
  public void testSetRed() {
    assertEquals(252, this.yellow.getRed());
    this.yellow.setRed(100);
    assertEquals(100, this.yellow.getRed());
    assertEquals(1, this.black.getRed());
    this.black.setRed(0);
    assertEquals(0, this.black.getRed());
  }

  @Test
  public void testSetRedZero() {
    assertEquals(252, this.yellow.getRed());
    this.yellow.setRed(0);
    assertEquals(0, this.yellow.getRed());
  }

  @Test
  public void testSetRedMax() {
    assertEquals(252, this.yellow.getRed());
    this.yellow.setRed(255);
    assertEquals(255, this.yellow.getRed());
  }

  @Test
  public void testSetRedNegative() {
    assertEquals(252, this.yellow.getRed());
    this.yellow.setRed(-10);
    assertEquals(0, this.yellow.getRed());
  }

  @Test
  public void testSetRedOverMax() {
    assertEquals(252, this.yellow.getRed());
    this.yellow.setRed(300);
    assertEquals(255, this.yellow.getRed());
  }

  @Test
  public void testSetGreen() {
    assertEquals(202, this.yellow.getGreen());
    this.yellow.setGreen(100);
    assertEquals(100, this.yellow.getGreen());
    assertEquals(1, this.black.getGreen());
    this.black.setGreen(0);
    assertEquals(0, this.black.getGreen());
  }

  @Test
  public void testSetGreenZero() {
    assertEquals(202, this.yellow.getGreen());
    this.yellow.setGreen(0);
    assertEquals(0, this.yellow.getGreen());
  }

  @Test
  public void testSetGreenMax() {
    assertEquals(202, this.yellow.getGreen());
    this.yellow.setGreen(255);
    assertEquals(255, this.yellow.getGreen());
  }

  @Test
  public void testSetGreenNegative() {
    assertEquals(202, this.yellow.getGreen());
    this.yellow.setGreen(-10);
    assertEquals(0, this.yellow.getGreen());
  }

  @Test
  public void testSetGreenOverMax() {
    assertEquals(202, this.yellow.getGreen());
    this.yellow.setGreen(300);
    assertEquals(255, this.yellow.getGreen());
  }

  @Test
  public void testSetBlue() {
    assertEquals(3, this.yellow.getBlue());
    this.yellow.setBlue(100);
    assertEquals(100, this.yellow.getBlue());
    assertEquals(1, this.black.getBlue());
    this.black.setBlue(0);
    assertEquals(0, this.black.getBlue());
  }

  @Test
  public void testSetBlueZero() {
    assertEquals(3, this.yellow.getBlue());
    this.yellow.setBlue(0);
    assertEquals(0, this.yellow.getBlue());
  }

  @Test
  public void testSetBlueMax() {
    assertEquals(3, this.yellow.getBlue());
    this.yellow.setBlue(255);
    assertEquals(255, this.yellow.getBlue());
  }

  @Test
  public void testSetBlueNegative() {
    assertEquals(3, this.yellow.getBlue());
    this.yellow.setBlue(-10);
    assertEquals(0, this.yellow.getBlue());
  }

  @Test
  public void testSetBlueOverMax() {
    assertEquals(3, this.yellow.getBlue());
    this.yellow.setBlue(300);
    assertEquals(255, this.yellow.getBlue());
  }

  @Test
  public void testGetValue() {
    assertEquals(1, this.black.getValue());
    assertEquals(0, this.white.getValue());
    assertEquals(252, this.yellow.getValue());
  }

  @Test
  public void testGetIntensity() {
    assertEquals(1, this.black.getIntensity(), 0.001);
    assertEquals(0, this.white.getIntensity(), 0.001);
    assertEquals(152.333, this.yellow.getIntensity(), 0.001);
  }

  @Test
  public void testGetLuma() {
    assertEquals(1, this.black.getLuma(), 0.001);
    assertEquals(0, this.white.getLuma(), 0.001);
    assertEquals(198.2622, this.yellow.getLuma(), 0.001);
  }
}