import model.Image;
import controller.ImageLoader;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImageTest {
  private Image image1;
  private Image image2;
  private Image image3;

  @Before
  public void init() {
    this.image1 = ImageLoader.load("images/test3x4.ppm");
    this.image2 = ImageLoader.load("images/Koala.ppm");
  }

  @Test
  public void testConstructor() {
  }

  // test invalid constructor cases?

  @Test
  public void testGetWidth() {
    assertEquals(3, this.image1.getWidth());
    assertEquals(1024, this.image2.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(4, this.image1.getHeight());
    assertEquals(768, this.image2.getHeight());
  }

  @Test
  public void testGetMaxValue() {
    assertEquals(255, this.image2.getMaxValue());
  }

  @Test
  public void testGetPixelAt() {
  }

  @Test
  public void testGetPixelAtNegativeRow() {
  }

  @Test
  public void testGetPixelAtTooLargeRow() {
  }

  @Test
  public void testGetPixelAtNegativeCol() {
  }

  @Test
  public void testGetPixelAtTooLargeCol() {
  }

  @Test
  public void testToPPM() {
    String image1PPM = "P3\n"
            + "3 4\n"
            + "255\n"
            + "252 186 3 252 227 3 161 252 3 \n"
            + "252 186 3 252 227 3 161 252 3 \n"
            + "252 186 3 252 227 3 161 252 3 \n"
            + "252 186 3 252 227 3 161 252 3 \n";
    assertEquals(image1PPM, this.image1.toPPMString());
  }
}
